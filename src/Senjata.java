public class Senjata extends Item{
    private int atk;
    private GameInfo objGameInfo = new GameInfo();

    public Senjata(String nama, int atk, int kodeItem) {
        super(nama, kodeItem);
        this.atk = atk;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    //method pakai Item
    @Override
    public void itemPakai(Player player) {
        if (this.isStatusPemakaian() == false){
            this.setStatusPemakaian(true);
            player.setAtk(player.getAtk()+this.atk);
            System.out.println("Item berhasil digunakan");
        }
        else{
            System.out.println("Sudah ada Senjata yang digunakan");
        }

    }

    @Override
    //method lepas item
    public void itemLepas(Player player) {
        if (this.isStatusPemakaian() == true){
            this.setStatusPemakaian(false);
            player.setAtk(player.getAtk()-this.atk);
            System.out.println("Item berhasil dilepas");
        }

        else{
            System.out.println("Belum ada Senjata yang digunakan");
        }
    }
}
