$banner = @"
===============================================
Easy Git Push v1.0, by Hadi
github: https://github.com/sansxpl/easy-gitpush
===============================================

"@

Write-Host $banner

# Function to load .gitconfig file
function Load-GitConfig {
    $configPath = Join-Path -Path $repositoryPath -ChildPath ".gitconfig"
    if (Test-Path $configPath) {
        $configContent = Get-Content $configPath
        $configHash = @{}
        foreach ($line in $configContent) {
            if ($line -match "^(\S+)\s*=\s*(.*)$") {
                $key = $matches[1]
                $value = $matches[2]
                # Trim the quotes manually
                $value = $value.Trim("'")
                $configHash[$key] = $value
            }
        }
        return $configHash
    }
    return $null
}

# Function to save .gitconfig file
function Save-GitConfig {
    $configPath = Join-Path -Path $repositoryPath -ChildPath ".gitconfig"
    $configContent = @"
origin = '$remoteOrigin'
repository = '$remoteRepo'
"@
    $configContent | Out-File -FilePath $configPath -Encoding utf8
}

# Get the current directory
$repositoryPath = Get-Location

# Check if .git directory exists to determine if it's a new or existing repository
if (Test-Path (Join-Path -Path $repositoryPath -ChildPath ".git")) {
    $gitConfig = Load-GitConfig

    if ($gitConfig -ne $null) {
        $remoteOrigin = $gitConfig.origin
        $remoteRepo = $gitConfig.repository
        Write-Host "Git config loaded from .gitconfig"
    } else {
        $remoteOrigin = Read-Host "Enter the origin name (e.g., origin)"
        $remoteRepo = Read-Host "Enter the remote repository (e.g., user/repo.git)"
        $remoteUrl = "https://github.com/" + $remoteRepo
        Save-GitConfig
    }

    $commitMessage = Read-Host "Enter the commit message"
    if ([string]::IsNullOrEmpty($commitMessage)) {
        Write-Host "Commit message cannot be empty." -ForegroundColor Red
        exit 1
    }

    # Display banner information
    Write-Host "`nOrigin: $remoteOrigin (from config)"
    Write-Host "Repository: $remoteRepo (from config)"
    Write-Host "Commit: $commitMessage`n"

    # Add and commit changes
    git add -A
    git commit -m $commitMessage

    # Push changes to the main branch
    git push -u $remoteOrigin main
} else {
    $remoteOrigin = Read-Host "Enter the origin name (e.g., origin)"
    $remoteRepo = Read-Host "Enter the remote repository (e.g., user/repo.git)"
    $remoteUrl = "https://github.com/" + $remoteRepo
    $commitMessage = Read-Host "Enter the commit message"
    if ([string]::IsNullOrEmpty($commitMessage)) {
        Write-Host "Commit message cannot be empty." -ForegroundColor Red
        exit 1
    }

    # Save the git configuration
    Save-GitConfig

    # Display banner information
    Write-Host "`nOrigin: $remoteOrigin"
    Write-Host "Repository: $remoteRepo"
    Write-Host "Commit: $commitMessage`n"

    Write-Host "Initializing new Git repository."

    # Initialize a new repository
    git init

    git add -A

    # Commit the changes
    git commit -m $commitMessage

    # Create the main branch
    git branch -M main

    # Add the remote repository URL
    git remote add $remoteOrigin $remoteUrl

    # Push to the main branch
    git push -u $remoteOrigin main
}

Write-Host "Git operations completed."
