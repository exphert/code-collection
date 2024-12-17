print("""
====================
TXT to VCF Converter
====================
""")

contact = str(input("Masukkan File List Kontak: ")) #Input list kontak
output = str(input("Masukkan Nama Output: ")) #Input file output

f = open(f"{output}.vcf", "w") #Buka file output + extensi `.vcf` agar siap buat di write (bisa pake "w" atau "a", "w" untuk write, "a" untuk append

#Buka list kontak trus baca perbaris
with open(contact, 'r') as file:
    for line in file:
        contactName = line.split(':')[0].strip() #Pisahkan pembatas `:` dan ambil nama kontak
        contactNumber = line.split(':')[1].strip() #Pisahkan pembatas `:` dan ambil nomor kontak
        
        #Tulis nama dan nomor kontak kedalam struktur file .vcf
        f.write(f"""
BEGIN:VCARD
VERSION:3.0
FN:{contactName}
TEL;TYPE=CELL:{contactNumber}
END:VCARD
        """)

f.close() #Tutup file output
print("Convert Sukses!") #Print sukses
