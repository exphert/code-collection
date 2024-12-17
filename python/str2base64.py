import argparse
import base64
import os

def encode_base64(input_data: bytes) -> str:
    """Encode bytes to a Base64 string."""
    return base64.b64encode(input_data).decode('utf-8')

def decode_base64(input_data: str) -> bytes:
    """Decode a Base64 string to bytes."""
    return base64.b64decode(input_data)

def file_mode(input_path: str, output_path: str, mode: str):
    """Handle encoding or decoding in file mode."""
    input_path = os.path.join(os.getcwd(), input_path)
    output_path = os.path.join(os.getcwd(), output_path)
    
    # Read input file
    with open(input_path, 'rb') as input_file:
        file_data = input_file.read()

    # Encode or decode
    if mode == 'encode':
        result = encode_base64(file_data).encode('utf-8')
    elif mode == 'decode':
        result = decode_base64(file_data)

    # Write output to file
    with open(output_path, 'wb') as output_file:
        output_file.write(result)

def raw_mode(input_raw: str, mode: str):
    """Handle encoding or decoding in raw input mode."""
    if mode == 'encode':
        result = encode_base64(input_raw.encode('utf-8'))
    elif mode == 'decode':
        result = decode_base64(input_raw).decode('utf-8')

    print(result)

def main():
    parser = argparse.ArgumentParser(description="Base64 encoder/decoder script.")
    parser.add_argument('--enc', action='store_true', help='Encode the input to Base64.')
    parser.add_argument('--dec', action='store_true', help='Decode the input from Base64.')
    parser.add_argument('-i', '--input', type=str, help='Input file path or raw input text.')
    parser.add_argument('-o', '--output', type=str, help='Output file path.')
    parser.add_argument('-ir', '--input_raw', type=str, help='Raw input text for encoding/decoding.')
    
    args = parser.parse_args()

    if args.enc and args.dec:
        print("Error: Cannot specify both --enc and --dec at the same time.")
        return

    mode = 'encode' if args.enc else 'decode' if args.dec else None
    if mode is None:
        print("Error: Please specify either --enc or --dec.")
        return

    if args.input and args.input_raw:
        print("Error: Cannot specify both --input and --input_raw.")
        return

    if args.input:  # File mode
        if not args.output:
            print("Error: Output file path is required when using input file.")
            return
        file_mode(args.input, args.output, mode)
    elif args.input_raw:  # Raw mode
        raw_mode(args.input_raw, mode)
    else:
        print("Error: Please provide either --input or --input_raw.")

if __name__ == '__main__':
    main()
