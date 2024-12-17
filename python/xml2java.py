import os
import requests

def send_post_request(input_file, output_file):
    # Get the current working directory
    current_directory = os.getcwd()

    # Create full paths for input and output files
    input_path = os.path.join(current_directory, input_file)
    output_path = os.path.join(current_directory, output_file)

    # Check if the input file exists
    if not os.path.exists(input_path):
        print(f"Input file does not exist: {input_path}")
        return

    # Read the content of the input file to use as POST data
    with open(input_path, 'r') as file:
        code_data = file.read()

    # Define the POST data
    post_data = {
        'code': code_data
    }

    # Send the POST request to the server
    try:
        print("Converting XML code, please wait...")
        response = requests.post('http://www.xmltojava.com/convert/', data=post_data)

        # Check if the request was successful
        if response.status_code == 200:
            print(f"Convert success, saving into output file [{output_path}].")
            # Write the response content to the output file
            with open(output_path, 'w') as file:
                file.write(response.text)
            print(f"Saved to {output_path}")
        else:
            print(f"Failed to convert. Error code: {response.status_code}")
    
    except Exception as e:
        print(f"Error: {e}")

# User input for file names (in the current directory)
input_file = input("Enter the input file name (XML file in current directory): ")
output_file = input("Enter the output file name (to save Java code): ")

send_post_request(input_file, output_file)
