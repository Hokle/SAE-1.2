class SAE extends Program{
    void algorithm(){
        inventaire inv = NewInvVide();
        remplissageAleatoire(inv);
        ajouterNombre(1,inv);
        vraiAffichage(inv);
        utiliserNombre(inv,4);
        utiliserNombre(inv,1);
        utiliserCarac(inv,'+');
        vraiAffichage(inv);
        ajouterNombre(9,inv);
        ajouterNombre(9,inv);
        ajouterSigne('%',inv);
        vraiAffichage(inv);
    } 

    signe NewSigne(char car, int NbDeFois){
        signe s = new signe();
        s.car = car;
        s.NbUtilisation = NbDeFois;
        return s;
    }

    nombre NewNombre(int nb, int NbUtilisation){
        nombre n = new nombre();
        n.nombre = nb;
        n.NbUtilisation = NbUtilisation;
        return n;
    }

    inventaire NewInvVide(){
        inventaire inv = new inventaire();
        char [] nb = new char[]{'+','-','*','/','%'};
        for(int i = 0 ; i < length(inv.nb) ; i++){
            inv.nb[i] = NewNombre(i+1,0);
        }
        for(int y = 0 ; y < length(inv.calc) ; y++){
            inv.calc[y] = NewSigne(nb[y],0);
        }
        return inv;
    }

    void vraiAffichage(inventaire inv){
        println("╔════════════════════════════════╦════════════════════════════════╗");
        print("║      ");
        print("Nombres disponibles       ║    Calculateurs disponibles    ");
        println("║");
        println("╠════════════════════════════════╬════════════════════════════════╣");
        print("║       ");
        for(int y = 0 ; y < length(inv.nb);y++){
            for(int i = 0 ; i < inv.nb[y].NbUtilisation;i++){
                print(inv.nb[y].nombre + " ");
            }
        }
        print("     ║           ");
        for(int y = 0 ; y < length(inv.calc);y++){
            for(int i = 0 ; i < inv.calc[y].NbUtilisation;i++){
                print(inv.calc[y].car+ " ");
            }
        }
        print("           ║");
        println();
        println("╚════════════════════════════════╩════════════════════════════════╝");
    }
    void afficherInventaire(inventaire inv){
        for(int i = 0 ; i < length(inv.nb) ; i++){
            print(inv.nb[i].nombre+" est disponible "+inv.nb[i].NbUtilisation+" fois. ");
            println();
        }
        println();
        for(int i = 0 ; i < length(inv.calc) ; i++){
            print(inv.calc[i].car+" est disponible "+inv.calc[i].NbUtilisation+" fois. ");
            println();
        }
    }
    
    boolean signeEstUtilisable(inventaire inv, char car){
        boolean possible = true;
        int i = 0;
        while(i < length(inv.calc) && possible){
            if(inv.calc[i].NbUtilisation == 0 && inv.calc[i].car == car){
                possible = false;
            }
            i = i + 1;
        }
        return possible;
    }

    boolean nombreEstUtilisable(inventaire inv, int car){
        boolean possible = true;
        int i = 0;
        while(i < length(inv.nb) && possible){ 
            if(inv.nb[i].NbUtilisation == 0 && inv.nb[i].nombre == car){
                possible = false;
            }
            i = i + 1;
        }
        return possible;
    }

    inventaire utiliserCarac(inventaire inv, char car){
        boolean trouve = false;
        int i = 0;
        while(!trouve && i < length(inv.calc)){
            if(inv.calc[i].car == car){
                inv.calc[i].NbUtilisation -= 1; 
                trouve = true;
            }
            i=i+1;
        }       
        return inv;
    }

    inventaire utiliserNombre(inventaire inv, int nb){
        boolean trouve = false;
        int i = 0;
        while(!trouve && i < length(inv.nb)){
            if(inv.nb[i].nombre == nb){
                inv.nb[i].NbUtilisation -= 1; 
                trouve = true;
            }
            i=i+1;
        }       
        return inv;
    }

    char caracRandom(){
        char [] car = new char[]{'+','+','+','+','-','-','*','*','/','%'};
        int alea = (int)(random()*10);
        return car[alea];
    }

    int nombreRandom(){
        int nb = (int)(random()*9)+1;
        return nb;
    }

    inventaire ajouterSigne(char car, inventaire inv){
        boolean trouve = false;
        int i = 0;
        while(!trouve && i < length(inv.calc)){
            if(inv.calc[i].car == car){
                inv.calc[i].NbUtilisation = inv.calc[i].NbUtilisation + 1;
                trouve = true;
            }
            i=i+1;
        }
        return inv;
    }
    
    inventaire ajouterNombre(int nb, inventaire inv){
        boolean trouve = false;
        int i = 0;
        while(!trouve && i < length(inv.nb)){
            if(inv.nb[i].nombre == nb){
                inv.nb[i].NbUtilisation = inv.nb[i].NbUtilisation + 1;
                trouve = true;
            }
            i=i+1;
        }
        return inv;
    }

    inventaire remplissageAleatoire(inventaire inv){
        for(int i = 0 ; i < length(inv.nb); i = i + 1){
            ajouterNombre(nombreRandom(),inv) ;
        }
        for(int y = 0 ; y < length(inv.calc); y = y + 1){
            ajouterSigne(caracRandom(),inv);         
        }
        return inv;
    }
} 