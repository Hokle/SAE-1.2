import extensions.File;
class SAE extends Program{
    void algorithm(){
        char[][] inv = initialiserInventaire(4);
        remplissageAleatoire(inv);
        utiliserCarac(inv,'+');
        afficherInventaire(inv);
    } 

    void afficherInventaire(char [][] inventaire){
        for(int i = 0 ; i < length(inventaire,1) ; i++){
            for(int y = 0 ; y < length(inventaire,2) ; y++){
                print(inventaire[i][y]);
            }
            println();
        }
    }
    
    int nbDeFoisCarac(char[][] inv, char car){
        int total = 0;
        for(int i = 0 ; i < length(inv,1) ; i++){
            for(int y = 0 ; y < length(inv,2) ; y++){
                if(inv[i][y] == car){
                    total = total + 1;
                }
            }
        }
        return total;
    }

    char[][] utiliserCarac(char[][] inv, char car){
        boolean trouve = false;
        int a = 0; int b = 0;
        while(!trouve && a < length(inv,1) && b < length(inv,2)){
            for(int i = 0 ; i < length(inv,2); i++){
                if(inv[a][i]== car){
                    inv[a][i] = '_'; 
                    trouve = true;
                }
            }
            a = a + 1 ;
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
        double a = random();
        char c;
        if(a < 0.4){
            c='+'; 
        }
        else if(a < 0.6){
            c= '-'; 
        }     
        else if(a < 0.8){
            c= '*'; 
        }     
        else if(a < 0.9){
            c= '/'; 
        } 
        else{
            c= '%'; 
        }   
        return c;
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
                  
                  
                  

