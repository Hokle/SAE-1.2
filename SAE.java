class SAE extends Program{
    void algorithm(){
        char[][] inventaire = InitialiserInventaire();
        int ind[] = RechercheCaseVide(inventaire);
        AjouterInventaire(inventaire, ind, '+');
    }

    char[][] InitialiserInventaire(){
        char[][] inv = new char[4][3];
        for(int i = 0 ; i < 4 ; i++){
            for(int y = 0 ; y < 3 ; y++){
                inv[i][y]=' ';
            }
        }
        return inv;
    }penis

    int[] RechercheCaseVide(char [][] inventaire){
        int[] tab = new int[2];
        for(int i = 0 ; i < length(tab,0);i++){
            for(int y = 0 ; y < length(tab,1); y++){
                if(inventaire[i][y]==' '){
                    tab[0] = i; 
                    tab[1] = y;
                }
            }
        }
        return tab;
    }

    boolean Estvide(char[][] inventaire){
        boolean res = false;
        return res;
    }

    boolean AjouterInventaire(char[][] inventaire, int[] IndiceCaseVide, char carac){
        boolean res = false;
        int[] ind = RechercheCaseVide(inventaire);
        if(ind[0] > -1 && ind[1] > -1)
            inventaire[ind[0]][ind[1]] = carac;
            res = true;
        return res;
    }
} 
