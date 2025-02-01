import requests
from colorama import Fore, Style
from datetime import datetime

def check_github_username(username):
    url = f"https://github.com/{username}"
    response = requests.get(url)
    return response.status_code == 200

def main():
    input_file = input("Enter the path to the username list file: ").strip()
    output_file = input("Enter the path to save the results: ").strip()

    with open(input_file, "r") as file:
        usernames = [line.strip() for line in file]

    results = []

    for i, username in enumerate(usernames, 1):
        exists = check_github_username(username)
        status = "[exist]" if exists else "[available]"
        color = Fore.RED if exists else Fore.GREEN
        print(f"{color}{i}. github.com/{username} {status}{Style.RESET_ALL}")
        results.append((i, username, status))

    print("\nSave options:")
    print("1. Save only [exist]")
    print("2. Save only [available]")
    print("3. Save all")
    choice = input("Choose an option (1/2/3): ").strip()

    current_date = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    scan_header = f"================[Scan result {current_date}]================\n"

    if choice in ["1", "2", "3"]:
        with open(output_file, "a") as output:
            output.write(scan_header)

            if choice in ["2", "3"]:
                output.write("\n[Available User]\n")
                for i, username, status in results:
                    if "[available]" in status:
                        output.write(f"{i}. github.com/{username}\n")

            if choice in ["1", "3"]:
                output.write("\n[Exist User]\n")
                for i, username, status in results:
                    if "[exist]" in status:
                        output.write(f"{i}. github.com/{username}\n")

if __name__ == "__main__":
    main()