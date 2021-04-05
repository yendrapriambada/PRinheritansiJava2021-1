public class Perisai extends Item{
    int defense;

    public Perisai(String nama, int defense, int kodeItem) {
        super(nama, kodeItem);
        this.defense = defense;
    }

    //method pakai Item
    @Override
    public void itemPakai(Player player) {
        if (this.isStatusPemakaian() == false){
            this.setStatusPemakaian(true);
            player.setDefense(player.getDefense()+this.defense);
            System.out.println("Item berhasil digunakan");
        }

        else{
            System.out.println("Sudah ada Perisai yang digunakan");
        }
    }

    @Override
    //method lepas item
    public void itemLepas(Player player) {
        if (this.isStatusPemakaian() == true){
            this.setStatusPemakaian(false);
            player.setAtk(player.getDefense()-this.defense);
            System.out.println("Item berhasil dilepas");
        }

        else{
            System.out.println("Belum ada Perisai yang digunakan");
        }
    }
}
