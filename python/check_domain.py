import requests
from colorama import Fore, Style, init

# Initialize colorama for Windows support
init(autoreset=True)

# Function to check the status code of a given domain using HTTP first, then HTTPS if HTTP fails
def check_status(domain, show_error):
    try:
        # First, try with HTTP
        response = requests.get(f"http://{domain.strip()}", timeout=5)
        return response.status_code
    except requests.exceptions.RequestException as http_error:
        if show_error:
            print(f"{Fore.YELLOW}Error accessing {domain} via HTTP: {http_error}. Trying HTTPS...")
        try:
            response = requests.get(f"https://{domain.strip()}", timeout=5)
            return response.status_code
        except requests.exceptions.RequestException as https_error:
            if show_error:
                print(f"{Fore.RED}Error accessing {domain} via both HTTP and HTTPS: {https_error}")
            return None  # Return None if both HTTP and HTTPS fail

# Function to save the output to a file
def save_output_to_file(output_file, counter, domain, status_code, error=False):
    try:
        with open(output_file, 'a') as f:  # Open in append mode
            if not error:
                f.write(f"{counter:02d} > Domain: {domain}, Status Code: {status_code}\n")
            else:
                f.write(f"{counter:02d} > Domain: {domain}, Status Code: N/A (Error)\n")
    except Exception as e:
        print(f"{Fore.RED}Failed to write to file: {e}")

# Function to read domains from the file and check status codes
def check_domains_from_file(domainName, filename, show_error, filter_status_codes, save_output, output_file):
    try:
        # Open the file with UTF-8 encoding to avoid UnicodeDecodeError
        with open(filename, 'r', encoding='utf-8') as file:
            counter = 1  # Counter for domain list
            # Read lines from the file
            for line in file:
                # For each domain, check its status
                domain = line.strip()  # Remove any surrounding whitespace or newline
                if domain:  # Skip empty lines
                    full_domain = domainName + domain  # Concatenate domainName and TLD
                    status_code = check_status(full_domain, show_error)

                    if status_code is not None:
                        # If filter is applied, only show the filtered status codes
                        if filter_status_codes and status_code not in filter_status_codes:
                            continue
                        
                        # Colorize the status code based on the value
                        if status_code == 200:
                            color = Fore.GREEN
                        elif 300 <= status_code < 400:
                            color = Fore.CYAN
                        elif 400 <= status_code < 500:
                            color = Fore.YELLOW
                        elif status_code >= 500:
                            color = Fore.RED
                        else:
                            color = Fore.WHITE
                        
                        # Show the counter, domain, and status code
                        print(f"{Fore.MAGENTA}{counter:02d} > {Style.RESET_ALL}Domain: {full_domain}, Status Code: {color}{status_code}")

                        # Save the output to the file if enabled
                        if save_output:
                            save_output_to_file(output_file, counter, full_domain, status_code)
                    else:
                        # If status_code is None, it means the domain couldn't be accessed
                        if show_error:
                            print(f"{Fore.MAGENTA}{counter:02d} > {Style.RESET_ALL}Domain: {full_domain}, {Fore.RED}Status Code: N/A")
                        # Save the error to the file if enabled
                        if save_output:
                            save_output_to_file(output_file, counter, full_domain, status_code=None, error=True)
                    counter += 1
    except FileNotFoundError:
        print(f"{Fore.RED}File {filename} not found!")
    except UnicodeDecodeError:
        print(f"{Fore.RED}Error decoding {filename}. Try using a different encoding.")

# Helper function to convert user input into a list of status codes
def parse_status_code_filter(input_string):
    try:
        # Split by commas and convert to integers
        return [int(code.strip()) for code in input_string.split(',') if code.strip()]
    except ValueError:
        print(f"{Fore.RED}Invalid input. Please enter comma-separated status codes like '200,404,500'.")
        return []

# Example usage:
domainName = input("Domain Name: ")  # Root domain, e.g., 'aris'
filename = input("Enter domain list path: ")  # Full path to the domain list file

# Ask user if they want to see errors (True/False)
show_error = input("Show error messages? (y/n): ").strip().lower() == 'y'

# Ask user for a filter on status codes (optional)
filter_input = input("Filter status code (e.g., 200,300,400) [Leave blank for no filter]: ").strip()
filter_status_codes = parse_status_code_filter(filter_input)

# Ask user if they want to save the output
save_output = input("Save output (y/n): ").strip().lower() == 'y'

output_file = None
if save_output:
    output_file = input("Enter output file path: ").strip()

print(f"\n")
# Call the main function with user inputs
check_domains_from_file(domainName, filename, show_error, filter_status_codes, save_output, output_file)
