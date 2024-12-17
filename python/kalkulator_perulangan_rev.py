

# tipe proses operasi, bisa menggunakan `if else` atau `switch case`
# keduanya sama-sama percabangan
# untuk menggunakan fungsi `if else` maka ubah valuenya ke `ifelse`
# untuk menggunakan fungsi `switch case` maka ubah valuenya ke `switch`
type_function = 'ifelse' #switch | ifelse

# Alias untuk nama operasinya, bisa di ubah sesuai kebutuhan
operator_alias = ['Kita', 'Mantan', 'Bersama', 'Sendirian', 'Bahagia']

# Ini fungsi untuk percabangan menggunakan `switch case`
def switch_case_operation(num1, num2, operator):
    match operator:
        case 1:
            return num1 + num2
        case 2:
            return num1 - num2
        case 3:
            return num1 * num2
        case 4:
            if num2 != 0:
                return num1 / num2
        case 5:
            return num1 ** num2

# Ini fungsi untuk percabangan menggunakan `if else`
def if_else_operation(num1, num2, operator):
    if operator == 1:
        return num1 + num2
    elif operator == 2:
        return num1 - num2
    elif operator == 3:
        return num1 * num2
    elif operator == 4:
        if num2 != 0:
            return num1 / num2
    elif operator == 5:
        return num1 ** num2

# Fungsi untuk program utama
def start_program():

    #Untuk mendapatkan semua user input
    my_input = input("Masukkan nilai Aku: ")
    you_input = input("Masukkan nilai Kamu: ")

    #Untuk mengecek, apakah user input berupa type data `integer` atau bukan
    try:

        #Jika integer maka ubah user input menjadi type data `integer`
        my_input = int(my_input)
        you_input = int(you_input)

    #Jika bukan maka ubah ke bentuk float/pecahan
    except:

        #Untuk mengecek, apakah user input berupa angka atau bukan
        try:

            #Jika angka dan bukan integer maka ubah ke bentuk float
            you_input = float(you_input)
            my_input = float(my_input)
        except:

            #Jika bukan angka maka akhiri program
            print("Tolong masukkan input berupa angka")
            exit()


    #Untuk mendapatkan operasi dari user input
    print(f"\nPilih operasi yang ingin dilakukan:\n1. {operator_alias[0]} (+)\n2. {operator_alias[1]} (-)\n3. {operator_alias[2]} (*)\n4. {operator_alias[3]} (/)\n5. {operator_alias[4]} (**)")
    choice = input("Masukkan nomor pilihan (1-5 atau 1,2,-): ")

    #Cek apakah operasinya terdapat tanda koma (,) atau tidak
    #Jika terdapat koma, maka ubah ke bentuk list/array
    if "," in choice:
        choice = choice.split(',')

    #Cek apakah operasinya berbentuk list/array atau bukan
    if isinstance(choice, list):

        #Jika berbentuk list/array maka gunakan perulangan menggunakan fungsi `for` sesuai dengan jumlah operasinya
        print(f"\nHasil:")
        for operator in choice:

            #Cek apakah operasinya angka atau bukan, jika angka maka ubah ke bentuk `integer`, jika bukan maka keluarkan pesan invalid
            try:
                operator = int(operator.strip())
            except:
                print(f"[!] Operator invalid ({operator}), tolong gunakan angka")

            #Cek apakah operasinya berada pada angka 1-5, jika bukan angka dari 1-5, maka keluarkan output pilihan operator tidak tersedia
            if operator in range(1, 6):

                #Proses hasilnya dan tampilkan outputnya
                hasil = if_else_operation(my_input, you_input, operator) if type_function == 'ifelse' else switch_case_operation(my_input, you_input, operator)
                print(f"> {operator_alias[operator - 1]}: {hasil}")
            else:
                print(f"Pilihan tidak tersedia ({operator})")
    else:

        #Jika bukan list/array, maka gunakan satu operasi
        #Cek apakah operasinya angka atau bukan, jika angka maka ubah ke bentuk `integer`, jika bukan maka akhiri program
        try:
            operator = int(choice.strip())
        except:
            print("[!] Operator invalid, tolong gunakan angka")
            exit()

        #Proses hasilnya dan tampilkan outputnya
        hasil = if_else_operation(my_input, you_input, operator) if type_function == 'ifelse' else switch_case_operation(my_input, you_input, operator)
        print(f"\nHasil {operator_alias[operator - 1]}: {hasil}")

if __name__ == '__main__':
    start_program()
