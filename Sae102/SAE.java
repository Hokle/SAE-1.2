class SAE extends Program{
    void algorithm(){
        char[][] inv = initialiserInventaire(4);
        remplissageAleatoire(inv);
        utiliserCarac(inv,'+');
        afficherInventaire(inv);
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
        inventaire i = new inventaire();
        return i;
    }

    void afficherInventaire(char [][] inventaire){
        for(int i = 0 ; i < length(inventaire,1) ; i++){
            for(int y = 0 ; y < length(inventaire,2) ; y++){
                print(inventaire[i][y]);
            }
            println();
        }
    }
    

    inventaire utiliserCarac(inventaire inv, char car){
        boolean trouve = false;
        int i = 0;
        while(!trouve && a < length(inv.calc)){
            if(inv.calc[i] == car){
                inv.calc[i].NbUtilisation -= 1; 
                trouve = true;
            }
            i=i+1;
        }       
        return inv;
    }

    char[][] initialiserInventaire(int taille){
        char[][] inv = new char[taille][taille];
        for(int i = 0 ; i < length(inv,1) ; i++){
            for(int y = 0 ; y < length(inv,2) ; y++){
                inv[i][y]='_';
            }
        }
        return inv;
    }

    int[] rechercheCaseVide(char [][] inventaire){
        int[] tab = new int[2];
        boolean trouve = false;
        int a = 0; int b = 0;
        while(!trouve && a < length(inventaire,1) && b < length(inventaire,2)){
            for(int i = 0 ; i < length(inventaire,2); i++){
                if(inventaire[a][b]== '_'){
                    tab[0] = a; 
                    tab[1] = b;
                    trouve = true;
                }
            }
            a = a + 1 ;
        }
        return tab;
    }

    boolean estRempli(char[][] inventaire){
        boolean remp = false;
        for(int i = 0 ; i < length(inventaire,1) ; i++){
            for(int y = 0 ; y < length(inventaire,2); y++){
                if(inventaire[i][y]!= '_'){
                    remp=true;
                }
            }
        }
        return remp;
    }

    boolean ajouterInventaire(char[][] inventaire, int[] IndiceCaseVide, char carac){
        boolean res = false;
        if(!estRempli(inventaire)){
            int[] i = rechercheCaseVide(inventaire);
            inventaire[i[0]][i[1]] = carac;
            res=true;
        }
        return res;
    }
    
    char[][] ajoutAleatoire(char[][] inv){
        int[] i = rechercheCaseVide(inv);
        inv[i[0]][i[1]] = caracRandom();        
        return inv;
    }

    char caracRandom(){
        char [] nb = new char[]{'+','+','+','+','-','-','*','*','/','%'};
        int alea = (int)(random()*10);
        return nb[alea];
    }

    char[][] remplissageAleatoire(char[][] inventaire){
        for(int y = 0 ; y < length(inventaire,1); y = y + 2){
            for(int i = 0 ; i < length(inventaire,2) ; i++){
                int a = (int)(random()*10);
                inventaire[i][y]= (char)(a+'0');
            }
        }
        for(int z = 1 ; z < length(inventaire,1); z = z + 2){
            for(int u = 0 ; u < length(inventaire,1) ; u++){
                inventaire[u][z]= caracRandom();  
            }
        }
        return inventaire;
    }
} 