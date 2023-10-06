# TP Libre

(J'ai oublié de faire des notes pour les premiers exercices et quelques autres trucs aussi, bref c'est amplement assez tout de même + flemme)

## Exercice 1.3

- Q1. Changez le mot de passe de l’utilisateur toto puis le mot de passe de root. Où sont stockés les mots de passe ? 
    - `/etc/shadow`, `/etc/passwd`
- Sous quelle forme ? Pouvez-vous y accéder en tant que toto ? En tant que root ? 
    - hash, non, oui
- Q2. Créez un utilisateur tata puis un groupe bidule dans lequel il y a toto et tata. 
    - `adduser tata`, `addgroup bidule`, `adduser toto bidule`, `adduser tata bidule`
- Q3. Créez un fichier et modifiez le propriétaire de ce fichier. Changez aussi son groupe. 
    - `chown toto:tata file`, `chown toto file`, `chgrp tata file`
    - Qui peut effectuer ces actions ? -> root
- Q4. Donnez les droits sudo à toto. 
    - `usermod -aG sudo toto` ou `adduser toto sudo`
- Q5. En étant connecté en tant que toto, affichez le contenu de /etc/shadow. À quoi correspond ce fichier ? Pourquoi vous faut-il les droits sudo pour le consulter ? 
    - fichier qui contient les mots de passe des utilisateurs, il faut les droits sudo pour le consulter car il est en lecture seule pour root
- Q6. Supprimez l’utilisateur tata. 
    - `userdel tata`

## Exercice 1.4

- `keyboard conf` -> `loadkeys` / `setxkbmap us` temporaire ou permanent avec `/etc/default/keyboard`
- `locale conf` -> `dpkg-reconfigure locales` / `/etc/default/locale` pour modifier directement dans le fichier 
- `su` -> switch user
- Changez la zone géographique pour que l’heure soit celle de Lille et non celle de Londres. 
    - `dpkg-reconfigure tzdata` ou `timedatectl set-timezone Europe/Paris`
- Le manuel a été installé en français. Changez sa langue pour qu’il soit en anglais. Même chose pour le passer en Allemand 
    - `dpkg-reconfigure locales` ou `man --locale=en` pour une solution isolée

J'me souviens peut-être plus des questions donc il y aura que ça, ouais.
## Exercice 2 : Le gestionnaire de paquets

- Q1. Qu’est-ce qu’un paquet dans le monde Unix ? 
    - fichier compressé contenant des fichiers et des métadonnées
- Q2. Qu’est-ce qu’un gestionnaire de paquets ? Que pouvez-vous faire avec ? 
    - outil qui permet de gérer les paquets, les installer, les supprimer, les mettre à jour, etc.
- Q3. Installez un programme présent dans les dépôts de debian (firefox par exemple). 
    - `apt install firefox`
- Q4. Installez un programme qui n’est pas présent dans les dépôts debian (codium par exemple). 
    - `dpkg -i codium.deb`
- Q5. Supprimez un programme installé (par exemple : Pluma). 
    - `apt remove pluma`
- Q6. Modifiez le miroir utilisé pour utiliser deb.debian.org. 
    - `/etc/apt/sources.list`
- Q7. En pratique vous préférez utiliser comme miroir celui qui répond le plus vite : Trouvez ce miroir et utilisez celui-ci. 
    - https://www.debian.org/mirror/list
- Q8. Installez le gestionnaire de bureau soit LXDE. Faites en sorte que votre debian ouvre LXDE par défaut. 
    - `apt install lxde`, `sudo update-alternatives --config x-session-manager`
        
## Exercice 4 : Partitions

- Q1. Qu’est-ce qu’une partition ? Comment les partitions fonctionnent-elle ? Pourquoi créer des partitions ? 
    - division d'un disque dur en plusieurs parties, chaque partition est indépendante des autres, on peut y mettre des systèmes d'exploitation différents, des données différentes, etc.
- Q2. Comment est partitionné actuellement le disque ? Combien y a-t-il d’espace libre ? 
    - `df -h`, 2 partitions, 1 de 20G et 1 de 4.9G, 4.9G d'espace libre
- Q3. Créez une partition (part1) de 15G sur la partie laissée vide de la machine virtuelle. Créez une partition (part2) utilisant le reste de l’espace disponible sur le disque.
    - `fdisk /dev/sda`, `n`, `p`, `1`, `15G`, `n`, `p`, `2`, `4.9G`, `w`
- Q4. Formatez les deux partitions créées. Quels formats pouvez-vous utiliser ? Tous les formats servent-ils tous à la même chose ? 
    - `mkfs.ext4 /dev/sda1`, `mkfs.ext4 /dev/sda2`, on peut utiliser ext2, ext3, ext4, btrfs, xfs, etc. Non, ils ne servent pas tous à la même chose, certains sont plus performants que d'autres, certains sont plus récents que d'autres, etc. 
- Q5. Vous souhaitez que part2 soit l’espace dédié au /home de votre système debian. Montez part2 dans /home et assurez vous que les données qui étaient présentes dans /home avant le montage soit toujours présentes. 
    - `mount /dev/sda2 /home`, les données sont toujours présentes
- Q6. Faites en sorte que la partition soit montée automatiquement au démarrage de votre machine. 
    - `/etc/fstab`
    
## Exercice 5 : Dual boot

- Q1. Installez un ubuntu sur la partition part1 (faites une installation minimale sinon il risque de ne pas y avoir assez de place), et assurez-vous que votre Ubuntu monte aussi part2 comme étant son /home et monte pour son /boot la même partition que votre debian. 
- Q2. Modifiez grub pour qu’il lance debian par défaut. 
    - `/etc/default/grub`, `GRUB_DEFAULT=0`, `GRUB_HIDDEN_TIMEOUT=0`, `GRUB_HIDDEN_TIMEOUT_QUIET=true`
    
## Exercice 6 : (Avancé) Fonctionnalité système

- Q1. Faite en sorte que Firefox s’ouvre automatiquement lorsque toto se connecte à sa session. 
    - `/etc/profile`
- Q2. Votre debian fonctionne grâce à un programme nommé systemd. Qu’est-ce que ce programme ? Que pouvez-vous faire avec ? 
    - gestionnaire de services, on peut lancer des services, les arrêter, les redémarrer, etc. C'est un daemon qui s'occupe de l'initialisation du système
- Q3. Faite en sorte que votre debian utilise comme afficheur graphique wayland. (Simplement installer wayland ne suffira pas.) 
    - `/etc/gdm3/custom.conf`, `WaylandEnable=true`

## Exercice 8 : (Avancé++) Gestion des noyaux

- Q1. Qu’est-ce que le noyau linux ? 
    - c'est le coeur du système d'exploitation, il gère les ressources matérielles, les processus, etc.
- Q2. Que pouvez-vous changer dans un noyau linux ? 
    - les modules, les options de compilation, etc.
- Q3. Ajoutez un module à votre noyau linux et recompilez votre noyau. 
    - https://www.howtoforge.com/roll_a_kernel_debian_ubuntu_way (tuto de compilation du noyau)
        - `apt install build-essential kernel-package libncurses5-dev fakeroot wget bzip2` si les paquets ne sont pas installés
        - `cp /boot/config-5.10.0-8-amd64 .config` copie la config du noyau actuel histoire de pas tout refaire (normalement c'est pas une bonne idée mais j'ai pas le temps, et j'ai pas envie de prendre une config minimaliste avec `make defconfig` vu qu'il n'est demandé d'ajouter qu'un module)
        - `make menuconfig` ajouter le module dans le noyau
        - `make-kpkg clean` supprimer les fichiers temporaires de compilation du noyau
        - `fakeroot make-kpkg --initrd --revision=1.0 kernel_image kernel_headers` créer les paquets du noyau compilé (image et headers) avec une révision de 1.0 même si jsp ce que ça fait
        - `dpkg -i linux-image-5.10.0_1.0_amd64.deb linux-headers-5.10.0_1.0_amd64.deb` installer les paquets du noyau compilé
- Q4. Assurez-vous de correctement démarrer sur le nouveau noyau. 
    - reboot et vérifier avec `uname -r` que le nouveau noyau soit bien utilisé 

Ps: J'ai cassé 5 fois ma VM pour les noyaux donc possible que ça soit pas exactement ce que j'ai fait, mais c'est l'idée, j'imagine, je crois, j'espère tsais la meuf grave hésitante.

En résumé: N'installez jamais linux, c'est des heures de votre vie qui seront perdues pour rien. (j'suis aigrie oui)