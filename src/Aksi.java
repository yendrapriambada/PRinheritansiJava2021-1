import javax.swing.plaf.multi.MultiRootPaneUI;
import java.util.ArrayList;
import java.util.Scanner;

public class Aksi {
    private ArrayList<String> arrAksi = new ArrayList<>();
    private Aksi objAksi;
    Scanner input = new Scanner(System.in);
    public Aksi (ArrayList arrAksi) {
        this.arrAksi = arrAksi;
    }

    public void printAksi () {
        int no=0;
        System.out.println("==============================================");
        for (String aksi: this.arrAksi) {
            no++;
            System.out.println(no + ". " + aksi);
        }
        System.out.println("==============================================");
    }

    public int pilihanAksiSub(int urutPil){
        int subPil = 0;
        for (String aksi: this.arrAksi) {
            subPil++;
            System.out.printf("%d%d. %s", urutPil, subPil, aksi);
            System.out.println();
        }
        System.out.println();
        return urutPil;
    }

    public int inputPilihanInt () {
        System.out.print("Masukkan Pilihan: ");
        int pilUser = input.nextInt();

        return pilUser;
    }

    //hardcode menu dikurangi
    public int pilihanAksiSub(String keterangan, ArrayList<Item> arrItem, int urutPil) {
        int subPil;
        //aksi2 item
        System.out.println(keterangan);
        for (Item objItem : arrItem) {
            urutPil++;
            subPil = 0;   //sistem penomorannya 11  12  13 dst
            System.out.println(objItem.getNama());
            //ambil pilihannya
            ArrayList<String> arrPil = objItem.getAksi();
            //print pilihan
            for (String strPil : arrPil) {
                subPil++;
                System.out.printf("%d%d. %s %n", urutPil, subPil, strPil);
            }
        }
        return urutPil;
    }

    public int pilihanAksiSub(ArrayList<Item> arrItem, ArrayList<String> arrAksiLepasPakai, int urutPil, int kodeItem) {
        int subPil = 0;
        //aksi2 item
        for (Item objItem : arrItem) {
            urutPil++;
            if (objItem.kodeItem == kodeItem){
                subPil = 0;   //sistem penomorannya 11  12  13 dst
                System.out.println(objItem.getNama());
                //ambil pilihannya
                ArrayList<String> arrPil = arrAksiLepasPakai;
                //print pilihan
                for (String strPil : arrPil) {
                    subPil++;
                    System.out.printf("%d%d. %s %n", urutPil, subPil, strPil);
                }
            }
        }
        return urutPil;
    }

    public int[] inputPilihanUserSub (){
        System.out.print("Pilihan anda?");
        String strPil = input.next();
        System.out.println("--");
        int ar[] = new int[2];
        int pil    =  Integer.parseInt(strPil.substring(0,1)); //ambil digit pertama, asumsikan jumlah tidak lebih dari 10
        int subPil =  Integer.parseInt(strPil.substring(1,2)); //ambil digit kedua, asumsikan jumlah tidak lebih dari 10

        ar[0] = pil;
        ar[1] = subPil;
        return ar;
    }

}
