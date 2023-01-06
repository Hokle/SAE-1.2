import extensions.File;
import extensions.CSVFile;

class jeu extends Program{
    int affichLgth = 0;
    CSVFile bossCSV = loadCSV("./boss/boss.csv");
    CSVFile leaderboardCSV = loadCSV("./leaderboard.csv");
    
    void algorithm() {
        /// Système de sauvegarde à X joueurs
        /// Système d'inventaire
        int choixMenu = 0;
        int choixRegle = 1;
        int choixBoard = 1;

        while(choixMenu == 0) {
            clearScreen();
            cursor(1,1);
            println("╔══════════════════════════════════════════════════════╗");
            println("║ Bienvenue dans Mathemageddon ! Choisissez une option ║");
            println("║                                                      ║");        
            println("║ 1 - Commencer une partie                             ║");
            println("║ 2 - Règles                                           ║");
            println("║ 3 - Leaderboard                                      ║");
            println("║                                                      ║");  
            println("╚══════════════════════════════════════════════════════╝");
            println();
            print("Choisissez une option : ");
            choixMenu = readInt();
        }

        if (choixMenu == 1) {

            print("Insérez votre pseudonyme : ");
            Score joueur = creerScore(readString(), 0);

            inventaire inv = NewInvVide();
            remplissageAleatoire(inv);
            ajouterNombre(nombreRandom(), inv);

            ///Boucle de chaque boss
            String bossSelected = choixBossRdm();
            Boss boss = creerBoss(bossSelected);
                ///Boucle de chaque tour
                printBoss(boss, initPrintBoss(bossSelected));

            affichageInv(inv);

        }
        if (choixMenu == 2) {
            while (choixRegle != 0) {
                clearScreen();
                cursor(1,1);
                println("╔═════════════════════════════════════════════════════════════════╗");
                println("║ Règles :                                                        ║");
                println("║                                                                 ║");        
                println("║ Déroulement d'une partie                                        ║");
                println("║     - Les boss s'enchainent aléatoirement                       ║");
                println("║     - La partie se termine lorsque le joueur n'a plus de vies   ║");
                println("║     - Le but est de battre le plus de boss                      ║");  
                println("║     - Pour infliger du dégât, il faut utiliser l'inventaire*    ║"); 
                println("║                                                                 ║"); 
                println("║ Inventaire                                                      ║"); 
                println("║     - L'inventaire possède des nombres et opérateurs aléatoires ║"); 
                println("║     - Il faut les combiner afin d'infliger du dégât             ║"); 
                println("║     - Les nombres et opérateurs utilisés seront remplacés       ║");
                println("║       aléatoirement pour le prochain tour !                     ║"); 
                println("║                                                                 ║");  
                println("╚═════════════════════════════════════════════════════════════════╝");
                println();
                print("0 - Retour au menu : ");
                choixRegle = readInt();
            }
        }
        if (choixMenu == 3) {
                while (choixBoard != 0) {
                clearScreen();
                cursor(1,1);
                Leaderboard();
                println();
                print("0 - Retour au menu : ");
                choixBoard = readInt();
            }
        }

        if (choixBoard == 0 || choixRegle == 0) {
            algorithm();
        }
    }

    String choixBossRdm() {
        int bossCSVLength = rowCount(bossCSV);
        int rdm = (int) (random() * bossCSVLength); 
        String[] bossNames = new String[bossCSVLength];
        for (int i = 0 ; i < bossCSVLength ; i++) {
            bossNames[i] = getCell(bossCSV, i, 2);
        }
        return bossNames[rdm];
    }

    void Leaderboard(){
        ///Récupérer les données de la partie
        triLeaderboard();
        printBoard(creerScore(getCell(leaderboardCSV, 0, 0), stringToInt(getCell(leaderboardCSV, 0, 1))),
                creerScore(getCell(leaderboardCSV, 1, 0), stringToInt(getCell(leaderboardCSV, 1, 1))),
                creerScore(getCell(leaderboardCSV, 2, 0), stringToInt(getCell(leaderboardCSV, 2, 1))),
                creerScore(getCell(leaderboardCSV, 3, 0), stringToInt(getCell(leaderboardCSV, 3, 1))),
                creerScore(getCell(leaderboardCSV, 4, 0), stringToInt(getCell(leaderboardCSV, 4, 1)))); 
    }

    void triLeaderboard() {
        String[][] leaderboard = new String[5][2];
        for (int i = 0 ; i < rowCount(leaderboardCSV) ; i++) {
            leaderboard[i][0] = getCell(leaderboardCSV, i, 0);
            leaderboard[i][1] = getCell(leaderboardCSV, i, 1);
        }
        for (int j = 0 ; j < length(leaderboard, 1)-1 ; j++) {
            String tPseudo = leaderboard[j][0];
            String tScore = leaderboard[j][1];
            if (stringToInt(tScore) < stringToInt(leaderboard[4][1])) {
                leaderboard[j][0] = leaderboard[4][0];
                leaderboard[j][1] = leaderboard[4][1];
                leaderboard[4][0] = tPseudo;
                leaderboard[4][1] = tScore;
            }
        }
        for (int j = 0 ; j < length(leaderboard, 1)-1 ; j++) {
            String tPseudo = leaderboard[j][0];
            String tScore = leaderboard[j][1];
            if (stringToInt(tScore) < stringToInt(leaderboard[3][1])) {
                leaderboard[j][0] = leaderboard[3][0];
                leaderboard[j][1] = leaderboard[3][1];
                leaderboard[3][0] = tPseudo;
                leaderboard[3][1] = tScore;
            }
        }
        for (int j = 0 ; j < length(leaderboard, 1)-1 ; j++) {
            String tPseudo = leaderboard[j][0];
            String tScore = leaderboard[j][1];
            if (stringToInt(tScore) > stringToInt(leaderboard[0][1])) {
                leaderboard[j][0] = leaderboard[0][0];
                leaderboard[j][1] = leaderboard[0][1];
                leaderboard[0][0] = tPseudo;
                leaderboard[0][1] = tScore;
            }
        }
        for (int j = 1 ; j < length(leaderboard, 1)-1 ; j++) {
            String tPseudo = leaderboard[j][0];
            String tScore = leaderboard[j][1];
            if (stringToInt(tScore) > stringToInt(leaderboard[1][1])) {
                leaderboard[j][0] = leaderboard[1][0];
                leaderboard[j][1] = leaderboard[1][1];
                leaderboard[1][0] = tPseudo;
                leaderboard[1][1] = tScore;
            }
        }
        saveCSV(leaderboard, "leaderboard.csv");
        leaderboardCSV = loadCSV("./leaderboard.csv");
    }

    Score creerScore (String pseudo, int result) {
        Score newScore = new Score();
        newScore.pseudo = pseudo;
        newScore.result = result;
        return newScore;
    }

    void printBoard (Score first, Score second, Score third, Score fourth, Score fifth) {
        int affichLgthBoardLeft = length(first.pseudo);
        if (affichLgthBoardLeft < length(second.pseudo)) {
            affichLgthBoardLeft = length(second.pseudo);
        }
        if (affichLgthBoardLeft < length(third.pseudo)) {
            affichLgthBoardLeft= length(third.pseudo);
        }
        if (affichLgthBoardLeft < length(fourth.pseudo)) {
            affichLgthBoardLeft = length(fourth.pseudo);
        }
        if (affichLgthBoardLeft < length(fifth.pseudo)) {
            affichLgthBoardLeft = length(fifth.pseudo);
        }
        
        print("╔");
        for (int i = 0 ; i < affichLgthBoardLeft+8 ; i++) {
            print("═");
        }
        println("╦═══════════╗");
        printBoardCase(first.pseudo, first.result, affichLgthBoardLeft);
        printLineBetweenCases(affichLgthBoardLeft);
        printBoardCase(second.pseudo, second.result, affichLgthBoardLeft);
        printLineBetweenCases(affichLgthBoardLeft);
        printBoardCase(third.pseudo, third.result, affichLgthBoardLeft);
        printLineBetweenCases(affichLgthBoardLeft);
        printBoardCase(fourth.pseudo, fourth.result, affichLgthBoardLeft);
        printLineBetweenCases(affichLgthBoardLeft);
        printBoardCase(fifth.pseudo, fifth.result, affichLgthBoardLeft);
        print("╚");
        for (int i = 0 ; i < affichLgthBoardLeft+8 ; i++) {
            print("═");
        }
        println("╩═══════════╝");
        println();
    }

    void printLineBetweenCases(int longueur) {
        print("╠");
        for (int i = 0 ; i < longueur+8 ; i++) {
            print("═");
        }
        println("╬═══════════╣");
    }

    void printBoardCase (String pseudo, int score, int longueur) {
        print("║");
        for (int i = 0 ; i < longueur+8 ; i++) {
            print(" ");
        }
        println("║           ║");
        print("║    ");
        print(pseudo);
        for (int i = 0 ; i < longueur-length(pseudo) ; i++) {
            print(" ");
        }
        print("    ║    ");
        String scoreFin = "";
        for (int j = 3 ; j > length("" + score) ; j--) {
            scoreFin += 0;
        }
        scoreFin += score;
        print(scoreFin);
        println("    ║"); 
        print("║");
        for (int i = 0 ; i < longueur+8 ; i++) {
            print(" ");
        }
        println("║           ║"); 
    }
    
    Operation creerOperation(int int1, char operateur, int int2){
        Operation newOperation = new Operation();
        newOperation.int1 = int1;
        newOperation.operateur = operateur;
        newOperation.int2 = int2;
        newOperation.result = calcOperation(int1, operateur, int2);
        return newOperation;
    }

    int calcOperation(int int1, char operateur, int int2){
        int result = int1;
        if (operateur == '+'){
            result += int2;
        }
        if (operateur == '-'){
            result -= int2;
        }
        if (operateur == '*'){
            result = result * int2;
        }
        if (operateur == '/'){
            result = result / int2;
        }
        if (operateur == '%'){
            result = result % int2;
        }
        return result;
    }

    void printOperation(Operation operation){
        print(operation.int1 + " " + operation.operateur + " " 
            + operation.int2 + " = " + operation.result);
        println();
    }

    Boss creerBoss(String nom) {
        Boss newBoss = new Boss();
        int i = 0;
        while (i < rowCount(bossCSV) && newBoss.nom != nom) {
            if (equals(nom, getCell(bossCSV, i, 2))) {
                newBoss.pv = stringToInt(getCell(bossCSV, i, 0));
                newBoss.force = stringToInt(getCell(bossCSV, i, 1));
                newBoss.nom = getCell(bossCSV, i, 2);
            }
            i++;
        }
        return newBoss;
    }

    String[] initPrintBoss (String boss) {
        File asciiArt = newFile("boss/" + boss + ".txt"); 
        int i = 0;
        while(ready(asciiArt)) {
            String currentLine = readLine(asciiArt);
            if (length(currentLine) > affichLgth) affichLgth = length(currentLine);
            i++;
        }
        String[] tabBoss = new String[i];
        File asciiArt2 = newFile("boss/" + boss + ".txt"); 
        int j = 0;
        while(ready(asciiArt2)) {
            String currentLine2 = readLine(asciiArt2);
            tabBoss[j++] = currentLine2;
        }
        return tabBoss;
    }

    void printTab(String[] boss) {
        for (int i = 0 ; i < length(boss) ; i++) {
            print("║ ");
            print(boss[i]);
            if (affichLgth > length(boss[i])) {
                for (int j = 0 ; j < affichLgth - length(boss[i]) ; j++) print(' ');
            }
            println(" ║");
        }
    }

    void printCaracteristique(String stat, String nStat) {
        String printedStat = "║ " + stat + nStat;
        print(printedStat);
        for(int i = 0 ; i < affichLgth - length(printedStat) ; i++) {
            print(' ');
        }
        print("   ║");
        println();
    }


    void printBoss(Boss boss, String[] design) {
        clearScreen();
        cursor(1,1);
        print("╔═");
        String ligneAffich = "";
        for(int i = 0 ; i < affichLgth ; i++) {
            ligneAffich += '═';
        }
        print(ligneAffich);
        println("═╗");
        printTab(design);
        printCaracteristique("", boss.nom);
        String bossPv = "";
        bossPv += boss.pv;
        printCaracteristique("Vies = ", bossPv);
        String bossForce = "";
        bossForce += boss.force;    
        printCaracteristique("Force = ", bossForce);
        print("╚═");
        print(ligneAffich);
        println("═╝");
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

    void affichageInv(inventaire inv){
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