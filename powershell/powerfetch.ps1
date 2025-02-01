# banner.ps1

# Function to get the uptime
function Get-Uptime {
    $uptime = (Get-CimInstance -ClassName Win32_OperatingSystem).LastBootUpTime
    $now = Get-Date
    $uptime = $now - $uptime
    return $uptime
}

$username = $env:USERNAME
$hostname = $env:COMPUTERNAME
$os = (Get-CimInstance -ClassName Win32_OperatingSystem).Caption
$kernel = (Get-CimInstance -ClassName Win32_OperatingSystem).Version
$uptime = (Get-Uptime).ToString("dd\.hh\:mm\:ss")
$shell = $PSVersionTable.PSVersion.ToString()
$resolution = (Get-WmiObject -Query "SELECT * FROM Win32_VideoController").VideoModeDescription
$cpu = (Get-WmiObject -Class Win32_Processor).Name
$gpu = (Get-WmiObject -Class Win32_VideoController).Name
$totalMemory = [math]::round((Get-CimInstance -ClassName Win32_OperatingSystem).TotalVisibleMemorySize / 1MB, 2)
$freeMemory = [math]::round((Get-CimInstance -ClassName Win32_OperatingSystem).FreePhysicalMemory / 1MB, 2)
$usedMemory = $totalMemory - $freeMemory
$disks = Get-CimInstance -Class CIM_LogicalDisk

# Custom ASCII Art Banners
$banner1 = @"
⡶⢫⣭⡝⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⣧⣹⡿⢃⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠁⠉⣗⣻⡃⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⢠⡀⢀⠀⠀⠀⠀⠀⠀⠀⡴⣚⣽⡛⢦
⠀⠀⣿⣨⣗⠀⢀⣤⡶⠟⠛⣉⣽⣶⣯⠿⠿⠿⢯⣷⢤⡀⠀⠀⠀⢦⣼⣿⠇⣸
⠀⠀⠀⠀⢀⣼⠟⠉⢀⣴⠟⠋⠀⡀⠀⠀⠀⠰⣆⠈⠙⢷⣆⠀⠀⣤⣏⣴⠾⠃
⠀⠀⠀⢠⣿⠃⠈⢠⡿⠃⠀⠀⣸⡁⠀⢰⠀⠀⢸⡄⠀⣄⠻⣧⠐⣯⣩⠟⠀⠀
⠀⠀⠀⣿⡇⠀⠂⣼⠃⢸⡄⠉⣽⠀⠀⣯⠀⠀⡘⣧⠀⢸⡄⢿⡇⠀⠀⠀⠀⠀
⠀⠀⢠⣿⠆⠈⢄⡟⠀⣸⠄⠠⣿⠀⠀⣿⠀⠀⣽⢷⡀⠈⡇⢸⣿⠀⠀⠀⠀⠀
⠀⠀⢰⣿⢠⡭⢠⡇⠀⢼⣂⣰⣿⣦⣠⣿⢯⣄⣾⣿⣦⣼⡇⢲⣿⡇⠀⠀⠀⠀
⠀⠀⣼⡏⣰⠃⣾⣇⠀⢺⠟⣿⣿⣿⡅⠀⠀⠀⣿⣿⣿⣹⠇⣼⣿⠃⠀⠀⠀⠀
⠀⢐⣿⠁⣯⠐⣿⢻⣀⠸⡇⠙⠿⠛⠀⠀⡀⠄⠈⠛⢋⢸⡄⣿⣏⠀⠀⠀⠀⠀
⠀⠈⣿⡌⣷⡌⠻⣽⡜⣎⡧⠐⠂⠐⠀⡀⠀⢀⠠⠁⠂⣼⢃⣿⡇⠀⠀⠀⠀⠀
⠀⠀⠉⣷⢾⣇⡳⢸⢷⣧⢻⣤⣤⣄⣡⣠⣔⣤⡴⡞⣿⣃⣾⣿⠃⠀⠀⠀⠀⠀
⠀⠀⠀⢸⢿⣻⣿⣿⢾⣟⣯⣧⠰⠌⡍⠛⠹⣶⣿⣿⣿⢲⣿⡟⠀⠀⠀⠀⠀⠀
⠀⠀⡀⣨⣾⣿⣿⣹⣾⡟⠙⠛⠉⡙⡙⣛⠉⠹⣿⣻⣿⣿⣝⡁⠀⠀⠀⠀⠀⠀
"@

$banner2 = @"
⠀⠀⠀⢸⣦⡀⠀⠀⠀⠀⢀⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⢸⣏⠻⣶⣤⡶⢾⡿⠁⠀⢠⣄⡀⢀⣴⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⣀⣼⠷⠀⠀⠁⢀⣿⠃⠀⠀⢀⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠴⣾⣯⣅⣀⠀⠀⠀⠈⢻⣦⡀⠒⠻⠿⣿⡿⠿⠓⠂⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠉⢻⡇⣤⣾⣿⣷⣿⣿⣤⠀⠀⣿⠁⠀⠀⠀⢀⣴⣿⣿⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠸⣿⡿⠏⠀⢀⠀⠀⠿⣶⣤⣤⣤⣄⣀⣴⣿⡿⢻⣿⡆⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠟⠁⠀⢀⣼⠀⠀⠀⠹⣿⣟⠿⠿⠿⡿⠋⠀⠘⣿⣇⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⢳⣶⣶⣿⣿⣇⣀⠀⠀⠙⣿⣆⠀⠀⠀⠀⠀⠀⠛⠿⣿⣦⣤⣀⠀⠀
⠀⠀⠀⠀⠀⠀⣹⣿⣿⣿⣿⠿⠋⠁⠀⣹⣿⠳⠀⠀⠀⠀⠀⠀⢀⣠⣽⣿⡿⠟⠃
⠀⠀⠀⠀⠀⢰⠿⠛⠻⢿⡇⠀⠀⠀⣰⣿⠏⠀⠀⢀⠀⠀⠀⣾⣿⠟⠋⠁⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠋⠀⠀⣰⣿⣿⣾⣿⠿⢿⣷⣀⢀⣿⡇⠁⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠋⠉⠁⠀⠀⠀⠀⠙⢿⣿⣿⠇⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀
"@

$banner3 = @"
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⡀⢉⣉⠉⠉⠋⠐⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠰⠒⠒⠂⠀⠄⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⣠⣾⣿⣿⡿⠿⠿⣶⣶⣥⣴⠢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢊⡀⢤⣤⣤⣶⣶⣶⣖⠃⠀⠀⠀⠀⠀
⠀⠀⠠⢴⣾⣿⡿⠋⠀⠀⠀⣴⣾⣿⣿⣿⣗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣥⣾⣿⣟⠉⠉⠙⠻⣿⣿⣦⣀⠀⠀⠀
⠀⠀⣠⣾⣿⠋⠀⠀⠀⠀⠠⣿⣜⢶⡗⣹⡯⠣⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣟⢭⣛⢹⣧⠀⠀⠀⠀⠻⣿⣧⡀⠀⠀
⠲⠿⠿⠿⣿⡀⠀⠀⠀⠀⠀⠻⠿⣷⡼⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣯⣫⣼⡏⠀⠀⠀⠀⠀⣹⣿⠿⠷⠦
⠀⠀⠀⠀⠈⠉⠒⠀⠀⡀⢀⣠⠶⠖⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠭⢭⢥⣄⠀⠀⠀⠀⠊⠁⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
"@

$banner4 = @"
░░░░█▐▄▒▒▒▌▌▒▒▌░▌▒▐▐▐▒▒▐▒▒▌▒▀▄▀▄░
░░░█▐▒▒▀▀▌░▀▀▀░░▀▀▀░░▀▀▄▌▌▐▒▒▒▌▐░
░░▐▒▒▀▀▄▐░▀▀▄▄░░░░░░░░░░░▐▒▌▒▒▐░▌
░░▐▒▌▒▒▒▌░▄▄▄▄█▄░░░░░░░▄▄▄▐▐▄▄▀░░
░░▌▐▒▒▒▐░░░░░░░░░░░░░▀█▄░░░░▌▌░░░
▄▀▒▒▌▒▒▐░░░░░░░▄░░▄░░░░░▀▀░░▌▌░░░
▄▄▀▒▐▒▒▐░░░░░░░▐▀▀▀▄▄▀░░░░░░▌▌░░░
░░░░█▌▒▒▌░░░░░▐▒▒▒▒▒▌░░░░░░▐▐▒▀▀▄
░░▄▀▒▒▒▒▐░░░░░▐▒▒▒▒▐░░░░░▄█▄▒▐▒▒▒
▄▀▒▒▒▒▒▄██▀▄▄░░▀▄▄▀░░▄▄▀█▄░█▀▒▒▒▒
"@

$banner5 = @"
⠀⠀⠀⠀⠀⠀⢀⣤⣶⣶⣖⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⢀⣾⡟⣉⣽⣿⢿⡿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⢠⣿⣿⣿⡗⠋⠙⡿⣷⢌⣿⣿⠀⠀⠀⠀⠀⠀⠀
⣷⣄⣀⣿⣿⣿⣿⣷⣦⣤⣾⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀
⠈⠙⠛⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⠀⢀⠀⠀⠀⠀
⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠻⠿⠿⠋⠀⠀⠀⠀
⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⡄
⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⢀⡾⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣷⣶⣴⣾⠏⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠛⠛⠋⠁⠀⠀⠀
"@

$banner6 = @"
⠀⠀⠀⠀⢀⣠⣤⣤⣤⣀⠀⠀⠀⠀⣀⣠⣤⣤⣤⣄⡀⠀⠀⠀⠀⠀
⠀⠀⣠⣿⠿⠛⠛⠛⠛⠛⢿⣷⣤⣾⠿⠛⠛⠙⠛⠛⠿⠗⠀⠀⠀⠀
⠀⣾⡿⠁⠀⠀⠀⠀⠀⠀⠀⠙⡿⠁⠀⢀⣤⣀⠀⠀⢀⣤⣶⡆⠀⠀
⢸⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀
⠸⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣧⣄⠀
⠀⢹⣿⠀⣿⣷⣄⣀⣤⡄⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⠷
⠀⠀⣁⣤⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠘⠛⠛⠛⠻⣿⣿⣿⠋⠉⠀⠀
⠀⠘⠻⢿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⢀⡀⠹⣿⡟⠀⠀⠀⠀
⠀⠀⠀⠀⢹⣿⠟⢙⠛⠛⠀⠀⠀⠀⠀⣀⣴⡿⠓⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠈⠁⠀⠈⠻⢿⣦⣄⠀⣠⣾⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
"@

$banner7 = @"
⣾⣿⠿⠿⠶⠿⢿⣿⣿⣿⣿⣦⣤⣄⢀⡅⢠⣾⣛⡉⠄⠄
⡋⣡⣴⣶⣶⡀⠄⠄⠙⢿⣿⣿⣿⣿⣿⣴⣿⣿⣿⢃⣤⣄
⣇⠻⣿⣿⣿⣧⣀⢀⣠⡌⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿
⣿⣷⣤⣤⣤⣬⣙⣛⢿⣿⣿⣿⣿⣿⣿⡿⣿⣿⡍⠄⠄⢀
⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⢇⣿⣿⡷⠶⠶⢿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⣷
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣌⣛⣻⣿⣿⣧⠙⠛⠛⡭⠅⠒⠦⠭⣭⡻⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠹⠈⢋⣽⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⠄⣴⣿⣶⣄⠄⣴⣶⠄⢀⣾⣿⣿⣿⣿
⠻⣿⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⠄⣿⣿⡀⣾⣿⣿⣿⣿⣛
⠀⠈⠛⢿⣿⣿⣿⠁⠞⢿⣿⣿⡄⢿⣿⡇⣸⣿⣿⠿⠛⠁
⠀⠀⠀⠀⠉⠻⣿⣿⣾⣦⡙⠻⣷⣾⣿⠃⠿⠋⠁
⠀⠀⠀⠀⠀⠀⠀⠙⠻⡿⣿⣿⡆⣿⡿⠃
"@

$banner8 = @"
⠀⠀⠀⠀⢀⣠⣤⣤⣤⣀⠀⠀⠀⠀⣀⣠⣤⣤⣤⣄⡀⠀⠀⠀⠀⠀
⠀⠀⣠⣿⠿⠛⠛⠛⠛⠛⢿⣷⣤⣾⠿⠛⠛⠙⠛⠛⠿⠗⠀⠀⠀⠀
⠀⣾⡿⠁⠀⠀⠀⠀⠀⠀⠀⠙⡿⠁⠀⢀⣤⣀⠀⠀⢀⣤⣶⡆⠀⠀
⢸⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀
⠸⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣧⣄⠀
⠀⢹⣿⠀⣿⣷⣄⣀⣤⡄⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⠷
⠀⠀⣁⣤⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠘⠛⠛⠛⠻⣿⣿⣿⠋⠉⠀⠀
⠀⠘⠻⢿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⢀⡀⠹⣿⡟⠀⠀⠀⠀
⠀⠀⠀⠀⢹⣿⠟⢙⠛⠛⠀⠀⠀⠀⠀⣀⣴⡿⠓⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠈⠁⠀⠈⠻⢿⣦⣄⠀⣠⣾⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
"@

$banner8 = @"
⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⠀
⠸⠿⣿⣿⣿⡿⠿⠿⣿⣿⣿⣶⣄⠀
⠀⠀⢸⣿⣿⡇⠀⠀⠀⠈⣿⣿⣿⠀
⠀⠀⢸⣿⣿⡇⠀⠀⢀⣠⣿⣿⠟⠀
⠀⠀⢸⣿⣿⡿⠿⠿⠿⣿⣿⣥⣄⠀
⠀⠀⢸⣿⣿⡇⠀⠀⠀⠀⢻⣿⣿⣧
⠀⠀⢸⣿⣿⡇⠀⠀⠀⠀⣼⣿⣿⣿
⢰⣶⣿⣿⣿⣷⣶⣶⣾⣿⣿⠿⠛⠁
⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⠀
"@


$banner9 = @"
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⢠⠄⠀⡐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠳⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⡈⣀⡴⢧⣀⠀⠀⣀⣠⠤⠤⠤⠤⣄⣀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠘⠏⢀⡴⠊⠁⠀⠄⠀⠀⠀⠀⠈⠙⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⣰⠋⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠘⢶⣶⣒⡶⠦⣠⣀⠀
⠀⠀⠀⠀⠀⠀⢀⣰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠈⣟⠲⡎⠙⢦⠈⢧
⠀⠀⠀⣠⢴⡾⢟⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡰⢃⡠⠋⣠⠋
⠐⠀⠞⣱⠋⢰⠁⢿⠀⠀⠀⠀⠄⢂⠀⠀⠀⠀⠀⣀⣠⠠⢖⣋⡥⢖⣩⠔⠊⠀⠀
⠈⠠⡀⠹⢤⣈⣙⠚⠶⠤⠤⠤⠴⠶⣒⣒⣚⣨⠭⢵⣒⣩⠬⢖⠏⠁⢀⣀⠀⠀⠀
⠀⠀⠈⠓⠒⠦⠍⠭⠭⣭⠭⠭⠭⠭⡿⡓⠒⠛⠉⠉⠀⠀⣠⠇⠀⠀⠘⠞⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⢤⣀⠀⠁⠀⠀⠀⠀⣀⡤⠞⠁⠀⣰⣆⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠿⠀⠀⠀⠀⠀⠉⠉⠙⠒⠒⠚⠉⠁⠀⠀⠀⠁⢣⡎⠁⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
"@

$banner10 = @"
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⣠⡶⡿⢿⣿⣛⣟⣿⡿⢿⢿⣷⣦⡀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢰⣯⣷⣿⣿⣿⢟⠃⢿⣟⣿⣿⣾⣷⣽⣺⢆⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢸⣿⢿⣾⢧⣏⡴⠀⠈⢿⣘⣿⢿⣿⣿⣿⣿⡆⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢹⣿⢠⡶⠒⢶⠀⠀⣠⠒⠒⠢⡀⢿⣿⣿⣿⡇⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣿⣿⠸⣄⣠⡾⠀⠀⠻⣀⣀⡼⠁⢸⣿⣿⣿⣿⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⠀⠀⠀
⠀⠀⠀⠀⠀⢰⣿⣿⠀⠀⠀⡔⠢⠤⠔⠒⢄⠀⠀⢸⣿⣿⣿⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠀⢸⣿⣿⣄⠀⠸⡀⠀⠀⠀⠀⢀⡇⠠⣸⣿⣿⣿⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠀⢸⣿⣿⣿⣷⣦⣮⣉⢉⠉⠩⠄⢴⣾⣿⣿⣿⣿⡇⠀⠀⠀⠀
⠀⠀⠀⠀⠀⢸⣿⣿⢻⣿⣟⢟⡁⠀⠀⠀⠀⢇⠻⣿⣿⣿⣿⣿⠀⠀⠀⠀
⠀⠀⠀⠀⠀⢸⠿⣿⡈⠋⠀⠀⡇⠀⠀⠀⢰⠃⢠⣿⡟⣿⣿⢻⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠸⡆⠛⠇⢀⡀⠀⡇⠀⠀⡞⠀⠀⣸⠟⡊⠁⠚⠌⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⡍⠨⠊⣒⠴⠀⡇⡴⠋⡋⢐⠐⠅⡀⠐⢠⠕⠂⢂⠀⠀⠀
"@

$banner11 = @"
⣠⣤⣤⡤⠤⢤⣤⣀⡀⠀⠐⠒⡄⠀⡠⠒⠀⠀⢀⣀⣤⠤⠤⣤⣤⣤⡄
⠈⠻⣿⡤⠤⡏⠀⠉⠙⠲⣄⠀⢰⢠⠃⢀⡤⠞⠋⠉⠈⢹⠤⢼⣿⠏⠀
⠀⠀⠘⣿⡅⠓⢒⡤⠤⠀⡈⠱⣄⣼⡴⠋⡀⠀⠤⢤⡒⠓⢬⣿⠃⠀⠀
⠀⠀⠀⠹⣿⣯⣐⢷⣀⣀⢤⡥⢾⣿⠷⢥⠤⣀⣀⣞⣢⣽⡿⠃⠀⠀⠀
⠀⠀⠀⠀⠈⢙⣿⠝⠀⢁⠔⡨⡺⡿⡕⢔⠀⡈⠐⠹⣟⠋⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⢼⣟⢦⢶⢅⠜⢰⠃⠀⢹⡌⢢⣸⠦⠴⣿⡇⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠘⣿⣇⡬⡌⢀⡟⠀⠀⠀⢷⠀⣧⢧⣵⣿⠂⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠈⢻⠛⠋⠉⠀⠀⠀⠀⠈⠉⠙⢻⡏⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢰⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠄⠀⠀⠀⠀⠀⠀⠀⠀
"@


$banner12 = @"
 ⣇⣿⠘⣿⣿⣿⡿⡿⣟⣟⢟⢟⢝⠵⡝⣿⡿⢂⣼⣿⣷⣌⠩⡫⡻⣝⠹⢿⣿⣷
​⡆⣿⣆⠱⣝⡵⣝⢅⠙⣿⢕⢕⢕⢕⢝⣥⢒⠅⣿⣿⣿⡿⣳⣌⠪⡪⣡⢑⢝⣇
​⡆⣿⣿⣦⠹⣳⣳⣕⢅⠈⢗⢕⢕⢕⢕⢕⢈⢆⠟⠋⠉⠁⠉⠉⠁⠈⠼⢐⢕⢽
​⡗⢰⣶⣶⣦⣝⢝⢕⢕⠅⡆⢕⢕⢕⢕⢕⣴⠏⣠⡶⠛⡉⡉⡛⢶⣦⡀⠐⣕⢕
​⡝⡄⢻⢟⣿⣿⣷⣕⣕⣅⣿⣔⣕⣵⣵⣿⣿⢠⣿⢠⣮⡈⣌⠨⠅⠹⣷⡀⢱⢕
​⡝⡵⠟⠈⢀⣀⣀⡀⠉⢿⣿⣿⣿⣿⣿⣿⣿⣼⣿⢈⡋⠴⢿⡟⣡⡇⣿⡇⡀⢕
​⡝⠁⣠⣾⠟⡉⡉⡉⠻⣦⣻⣿⣿⣿⣿⣿⣿⣿⣿⣧⠸⣿⣦⣥⣿⡇⡿⣰⢗⢄
​⠁⢰⣿⡏⣴⣌⠈⣌⠡⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣬⣉⣉⣁⣄⢖⢕⢕⢕
​⡀⢻⣿⡇⢙⠁⠴⢿⡟⣡⡆⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣵⣵⣿
​⡻⣄⣻⣿⣌⠘⢿⣷⣥⣿⠇⣿⣿⣿⣿⣿⣿⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
​⣷⢄⠻⣿⣟⠿⠦⠍⠉⣡⣾⣿⣿⣿⣿⣿⣿⢸⣿⣦⠙⣿⣿⣿⣿⣿⣿⣿⣿⠟
​⡕⡑⣑⣈⣻⢗⢟⢞⢝⣻⣿⣿⣿⣿⣿⣿⣿⠸⣿⠿⠃⣿⣿⣿⣿⣿⣿⡿⠁⣠
​⡝⡵⡈⢟⢕⢕⢕⢕⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣿⣿⣿⣿⣿⠿⠋⣀⣈⠙
 ⡝⡵⡕⡀⠑⠳⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⢉⡠⡲⡫⡪⡪⡣
"@



# Create an array of banners
#$banners = @($banner1, $banner2, $banner3, $banner4, $banner5, $banner6, $banner7,$banner8,$banner9,$banner10,$banner11)

# Select a random banner
#$randomBanner = Get-Random -InputObject $banners
$randomBanner = $banner12

# Split the selected banner into lines
$bannerLines = $randomBanner -split "`r`n"

# Ensure disk size is not zero or null to prevent division by zero
$cnt = 0
foreach ($disk in $disks) {

if($cnt -eq 0){
$diskUsage = "$([math]::round(($disk.Size - $disk.FreeSpace) / 1GB, 2)) GiB / $([math]::round($disk.Size / 1GB, 2)) GiB ($([math]::round((($disk.Size - $disk.FreeSpace) / $disk.Size) * 100, 0))%)"
}
$cnt = 1
}


# Define the information lines
$infoLines = @(
    "$username λ $hostname",
    "----------",
    "OS: $os",
    "Kernel: $kernel",
    "Uptime: $uptime",
    "Shell: PowerShell v$shell",
    "Resolution: $resolution",
    "CPU: $cpu",
    "GPU: $gpu",
    "Memory: $usedMemory GiB / $totalMemory GiB ($([math]::round(($usedMemory / $totalMemory) * 100, 0))%)",
    "Disk (C): $diskUsage"
)

# Find the maximum length of the banner lines
$maxBannerLength = ($bannerLines | Measure-Object -Property Length -Maximum).Maximum

Write-Host ("")
# Print the banner and information side by side
for ($i = 0; $i -lt $bannerLines.Length -or $i -lt $infoLines.Length; $i++) {
    $bannerLine = ""
    $infoLine = ""

    if ($i -lt $bannerLines.Length) {
        $bannerLine = $bannerLines[$i]
    }
    if ($i -lt $infoLines.Length) {
        $infoLine = $infoLines[$i]
    }

    # Print the banner line in Cyan and the info line in Yellow
    Write-Host (" "+$bannerLine.PadRight($maxBannerLength)) -ForegroundColor Blue -NoNewline

    if ($infoLine -match "λ") {
        # If the line contains 'λ', treat it as 'user@host'
        $infoParts = $infoLine -split "λ", 2
        if ($infoParts.Length -eq 2) {
            $title = $infoParts[0].Trim()
            $value = $infoParts[1].Trim()
            
            # Print title in Magenta and value in White
            Write-Host ("  " + $title) -ForegroundColor Blue -NoNewline
            Write-Host ("λ") -ForegroundColor White -NoNewline
            Write-Host $value -ForegroundColor Cyan
        } else {
            # Print the line if it does not contain a colon
            Write-Host ("  " + $infoLine) -ForegroundColor Cyan
        }
    } else {
        # Split the info line into title and value
        $infoParts = $infoLine -split ":", 2
        if ($infoParts.Length -eq 2) {
            $title = $infoParts[0].Trim()
            $value = $infoParts[1].Trim()
            
            # Print title in Magenta and value in White
            Write-Host ("  " + $title + ": ") -ForegroundColor Magenta -NoNewline
            Write-Host $value -ForegroundColor White
        } else {
            # Print the line if it does not contain a colon
            Write-Host ("  " + $infoLine) -ForegroundColor White
        }
    }
}

Write-Host ("")
