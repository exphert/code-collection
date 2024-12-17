
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class BankCLI {

    public static String ORIGINAL_BY_SURYA = "SURYA_HADINI_SUBAGJA_24552011357";

    public static void main(String[] args) {
        int saldo = 5000;
        int kode;

        do {
            clear();
            print("==========[BANK CLI]==========", 0);
            print("0. Setor Tunai", 0);
            print("1. Tarik Tunai", 0);
            print("88. Keluar", 0);
            print("==============================", 0);
            print("Saldo di Bank: Rp." + format(saldo), 0);
            print("==============================", 0);
            print("> Pilih 0/1/88: ", 1);
            kode = scanint();

            if (kode == 0) {
                clear();
                print("=========[Setor Tunai]========", 0);
                print("Total Saldo: Rp." + format(saldo), 0);
                print("0. Kembali", 0);
                print("==============================", 0);
                print("> Jumlah: ", 1);
                int setor = scanint();
                if (setor != 0) {
                    saldo += setor;
                    print("Setor tunai berhasil!", 0);
                    pause();
                }
            } else if (kode == 1) {
                int tarik;
                do {
                    clear();
                    print("=========[Tarik Tunai]========", 0);
                    print("Sisa Saldo: Rp." + format(saldo), 0);
                    print("0. Kembali", 0);
                    print("==============================", 0);
                    print("> Jumlah: ", 1);
                    tarik = scanint();
                    if (tarik != 0) {
                        if (tarik <= saldo && (saldo - tarik) >= 5000) {
                            saldo -= tarik;
                            print("Tarik tunai berhasil!", 0);
                            pause();
                        } else {
                            print("Saldo tidak cukup!", 0);
                            print("Saldo minimal di akun harus Rp.5,000.00!", 0);
                            pause();
                        }
                    }
                } while (tarik != 0);
            }
        } while (kode != 88);
        print("\nTerimakasih", 0);

    }

    public static void pause() {
        try {System.in.read();} catch (IOException ignored) {}
    }

    public static void print(String str, Integer Mode) {
        if (Mode == 0) {
            System.out.println(str);
        } else {
            System.out.print(str);
        }
    }

    public static Integer scanint() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String format(double currency) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setCurrency(Currency.getInstance("IDR"));
        return (currencyFormat.format(currency)).replaceAll("IDR", "");
    }

    public static void clear() {
        if (!new String(java.util.Base64.getDecoder().decode(
                ("\u0055\u0031\u0056\u0053\u0057\u0055\u0046\u0066\u0053\u0045\u0046\u0045\u0053\u0055\u0035\u004A\u0058\u0031\u004E\u0056\u0051\u006B\u0046\u0048\u0053\u006B\u0046\u0066\u004D\u006A\u0051\u0031\u004E\u0054\u0049\u0077\u004D\u0054\u0045\u007A\u004E\u0054\u0063\u003D")
                        .getBytes()))
                .equals(ORIGINAL_BY_SURYA)) {
            System.out.println("Jangan di ganti ya :)");
            System.exit(0);
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
