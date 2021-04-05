# PRinheritansiJava2021

--
====================================
TUGAS PEMROGRAMAN BERORIENTASI OBJEK
====================================
Pendidikan Ilmu Komputer B 2019 Semester 4 Universitas Pendidikan Indonesia
Nama Anggota:
1. Dwi Fitria Al Husaeni	- 1903480
2. Mochamad Yendra Priambada 	- 1900056

Introduction Game:
Terdapat sebuah player yang disekap pada sebuah rumah tua.
Ia harus melalui dua buah ruangan untuk dapat keluar dari rumah tersebut.
Terdapat beberapa item yang harus ia dapatkan. Salah satunya adalah box Kayu
yang berisi kunci pintu keluar dari ruangan terakhir, Akan tetapi box Kayu
tersebut tidak bisa di buka dan memerlukan sebuah kunci untuk membukanya. 
Mari kita Bantu player untuk menemukan kunci box Kayu tersebut dan jangan
lupa untuk mengalahkan musuh yang ada di salah satu ruangan!!

Deskripsi modifikasi/tambahan dari kode sebelumnya:
1. Tambahan Class
   - Class Aksi 
   - Class Karakter
   - Class Musuh
   - Class Perisai
   - Class Pertarungan
   - Class Senjata
2. Tambahan Inheritance
   - Parent Class Karakter
     -- Class turunan : NPC, Musuh, dan Player
   - Parent Class Item
     -- Class turunan : Pintu, Senjata, dan Perisai
3. Perbaiki
   - Memisahkan method aksi dan beberapa method menuAksi di setiap class 
     menjadi sebuah class baru yakni class aksi
   - Pada class player, proses aksi yang memiliki alur 
     seperti class ruangan diganti dengan memanggil 
     method di class aksi, begitupun di class ruangan dan class gameEngine
   - Menjadikan class pintu sebagai class turunan dari class item
   - Class NPC dan Class Player dijadikan class turunan dari class Karakter
   - Penambahan komentar pada kode agar lebih mudah dimengerti
   - Memperbaiki method dan atribut yang redundansi

Tambahan Fitur Pada Game:
1. Menambah 1 ruangan sehingga terdapat 2 ruangan
2. Menambah class musuh dengan atribut kesehatan, atk, defense, dan beberapa method lainnya
3. Menambah beberapa objek item di ruangan 1 
4. Adanya perubahan ketentuan untuk menyelesaikan misi
5. Menambahkan 1 item pintu di setiap ruangan (1 pintu ruangan 1 & 1 pintu ruangan 2)
6. Terdapat fitur pertarungan antara musuh dan player
7. Menambah story line misi
8. Menambahkan syarat khusus untuk mengambil suatu item maupun membuka pintu dan menghubungi NPC
9. Player dapat memakai dan melepaskan senjata dan perisai yang ia temukan dalam suatu ruangan
10. Menambahkan atribut baru pada player yakni atk dan defense

Ketentuan Pertarungan:
1. Dilakukan secara bergiliran antara musuh dan player
2. Dapat memilih arah serangan maupun arah penghindaran
3. Pengurangan kesehatan musuh = musuh - (atkPlayer - defenseMusuh)
4. Pengurangan kesehatan player = 
   - cek terlebih dahulu defense player, 
   - jika defense player >= atkMusuh maka kesehatan 
     player bisa jadi tidak akan berkurang.
   - Namun jika sebaliknya, atkMusuh > defensePlayer, makas
     kesehatan player - (atkMusuh - defensePlayer)
5. Player dapat memilih 2 pilihan dalam pertarungan, kabur dan menyerang.
