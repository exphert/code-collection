import os
import random

def rename_files(folder_name, option):
    try:
        # Get the current working directory
        current_directory = os.getcwd()

        # Create the full path to the folder
        folder_path = os.path.join(current_directory, folder_name)

        # Check if the folder exists
        if not os.path.isdir(folder_path):
            print(f"Folder does not exist: {folder_path}")
            return

        # Get the list of files in the folder
        for filename in os.listdir(folder_path):
            file_path = os.path.join(folder_path, filename)
            
            # Only rename files (skip directories)
            if os.path.isfile(file_path):
                # Split filename into name and extension
                name, extension = os.path.splitext(filename)

                # Apply the chosen option to modify the name only
                if option == 'lower':
                    new_name = name.lower()
                elif option == 'upper':
                    new_name = name.upper()
                elif option == 'random':
                    new_name = ''.join(random.choice([c.lower(), c.upper()]) for c in name)
                elif option == 'title':
                    new_name = name.capitalize()  # Capitalize first letter of the name
                elif option == 'capital':
                    new_name = name.title()  # Capitalize first letter of each word in the name
                else:
                    print(f"Unknown option: {option}")
                    return

                # Create the new filename by keeping the extension unchanged
                new_filename = new_name + extension
                new_file_path = os.path.join(folder_path, new_filename)
                
                # Rename the file
                os.rename(file_path, new_file_path)
                print(f"Renamed: {filename} -> {new_filename}")
        
        print("\nRenaming complete.")
    
    except Exception as e:
        print(f"Error: {e}")

# User input for folder name (relative to the current directory)
folder_name = input("Enter the folder name (relative to the current directory): ")

# User input for the renaming option
print("Choose a renaming option:")
print("1. lower")
print("2. upper")
print("3. random")
print("4. title (capitalize first letter)")
print("5. capital (capitalize first letter of each word)")

option_choice = input("Enter the number of your choice: ")

# Map the user's choice to the corresponding option
options = {
    '1': 'lower',
    '2': 'upper',
    '3': 'random',
    '4': 'title',
    '5': 'capital'
}

# Get the renaming option based on the user's input
option = options.get(option_choice)

if option:
    print(f"")
    rename_files(folder_name, option)
else:
    print("Invalid choice. Please enter a valid number.")
