import java.util.ArrayList;
import java.util.Scanner;

public class Pertarungan {
    private int jumlahPenghindaranMusuh; //jumlah penghindaran musuh
    private int jumlahPenghindaranPlayer; // jumlah menghindar yang dilakukan oleh player
    private boolean gameOver = false;   // variabel yang menunjukan apakah pertarungan selesai atau belum
    ArrayList<String> arrArah = new ArrayList<>(); //menunjukan arah penyerangan dan ujuga arah penghindaran player
    private Aksi objAksi;

    Scanner input = new Scanner(System.in);

    public Pertarungan() {
        //init arah serang
        arrArah.add("Kanan");
        arrArah.add("Bawah");
        arrArah.add("Kiri");
        arrArah.add("Atas");
        objAksi = new Aksi(arrArah);
    }

    public void cekKondisiMusuh(Player player, Musuh musuh) {
        if (musuh.isDie()) {
            //jika is Die == true
            System.out.println("Musuh sudah dikalahkan");
        } else {
            //jika is Die == false
            perang(player, musuh);
        }
    }

    //aksi perang = proses bergiliran penyerangan natara musuh dan player
    public void perang(Player player, Musuh musuh) {
        int jumlahSerang = 0;
        //init jumlah maksimal penghindaran
        jumlahPenghindaranMusuh = 3;
        jumlahPenghindaranPlayer = 3;
        System.out.println(" =================================== ");
        System.out.println("             Serang Musuh ");
        System.out.println(" =================================== ");

        while (true) {
            //pengecekana apakah pertarungan sudah berakhir atau belum
            if (!gameOver) {
                if (jumlahSerang % 2 == 0) {
                    //jumlah penyerangan genap giliran player yang menyerang
                    if (player.getKesehatan() > 0) {
                        System.out.println(" =================================== ");
                        System.out.println("       Giliran Anda Menyerang");
                        System.out.println(" =================================== ");
                        menuSerangPlayer(player,musuh);
                        jumlahSerang++;
                        if (musuh.getKesehatan() <= 0) {
                            //set musuh meninggal true
                            musuh.setDie(true);
                            System.out.println(" =================================== ");
                            System.out.println("             Musuh Kalah");
                            System.out.println(" =================================== ");
                            break;
                        }
                    }
                } else {
                    if (musuh.getKesehatan() > 0) {
                        //jumlah penyerangan ganjil giliran musuh yang menyerang
                        System.out.println(" =================================== ");
                        System.out.println("      Giliran Musuh Menyerang");
                        System.out.println(" =================================== ");
                        int arahMusuh = (int) (Math.random() * 3);
                        penyeranganMusuh(player, arahMusuh, musuh);
                        jumlahSerang++;
                        if (player.getKesehatan() <= 0) {
                            //player kalah
                            System.out.println(" =================================== ");
                            System.out.println("            Player Kalah");
                            System.out.println(" =================================== ");
                            break;
                        }
                    }
                }
            } else {
                player.setKesehatan(0);     //set kesehatan player 0
                System.out.println("-----------------------------------------------------");
                System.out.println("Anda melarihan diri");
                System.out.println("-----------------------------------------------------");
                break;
            }
        }
    }

    // method untuk memilih arah serang
    public int menuPilihArah(){
        objAksi.printAksi();
        int pilihanarah = objAksi.inputPilihanInt();
        return pilihanarah-1;
    }

    public void menuSerangPlayer(Player player, Musuh musuh) {
        boolean status = true;
        while (status) {
            System.out.println("1. Menyerang");
            System.out.println("2. Kabur"); //otomatis kalah
            System.out.print("Pilihan anda: ");
            int pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    while (true) {
                        int arahMusuh = (int) (Math.random() * 3);
                        System.out.println("Pilih arah penyerangan");
                        int arahserang = menuPilihArah();
                        if (jumlahPenghindaranPlayer > 0) {
                            boolean hasilSerangan = false;
                            if (arahserang < arrArah.size() && arahserang >=0) {
                                hasilSerangan = cekSerangan(arahMusuh, arahserang);
                            } else {
                                System.out.println("Anda memilih arah penyerangan yang tidak tersedia");
                                System.out.println("penyerangan dianggap gagal");
                            }
                            if (hasilSerangan) {
                                player.serangKurangiHp(musuh);
                                System.out.println("Serangan berhasil");
                            } else {
                                System.out.println("Serangan gagal, musuh menghindar");
                                jumlahPenghindaranPlayer--;
                            }

                            cekKondisi(player, musuh);
                            break;
                        } else {
                            player.serangKurangiHp(musuh);
                            System.out.println("Serangan berhasil");
                            cekKondisi(player, musuh);
                            break;
                        }
                    }
                    status = false;
                    break;
                case 2:
                    status = false;
                    gameOver = true;
                    break;
                default:
                    System.out.println("Masukan anda tidak tersedia");
                    break;
            }
        }
    }

    public void penyeranganMusuh(Player player, int arahMusuh, Musuh musuh){
//        System.out.println("Anda hanya dapat menghindar sebanyak 3 kali kesempatan");
//        System.out.println("Jumlah Kesempatan Penghindaran : "+getJumlahPenghindaran());
        System.out.println("Pilih arah penghindaran");
        int arahHindarPlayer = menuPilihArah();
        boolean hasilSerangan = false;

        if (jumlahPenghindaranMusuh > 0) {
            if (arahHindarPlayer < arrArah.size() && arahHindarPlayer >= 0) {
                hasilSerangan =  cekSerangan(arahMusuh, arahHindarPlayer);
            } else {
                System.out.println("Anda memilih arah penghindaran yang tidak tersedia");
                System.out.println("Penghindaran dianggap gagal");
            }

            if (!hasilSerangan){
                musuh.serangKurangiHp();
                System.out.println("Anda terkena Serangan!");
            }
            else{
                System.out.println("Serangan gagal, anda berhasil menghindar!");
                jumlahPenghindaranMusuh--;
            }
        } else {
            musuh.serangKurangiHp();
            System.out.println("Anda terkena Serangan!");
        }
        cekKondisi(player, musuh);
    }

    public boolean cekSerangan(int arahM, int arahS){
        boolean hasilSerangan = false;
        if (arahM==arahS){
            hasilSerangan = true;
        }
        else{
            hasilSerangan = false;
        }
        return hasilSerangan;
    }

    public void cekKondisi (Player player, Musuh musuh){
        System.out.println("----------------------------------------------");
        System.out.println("Sisa Hp Anda : "+player.getKesehatan());
        System.out.println("Sisa Hp Musuh : "+musuh.getKesehatan());
        System.out.println("----------------------------------------------");
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
