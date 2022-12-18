import extensions.File;
import extensions.CSVFile;

class jeu extends Program{
    int affichLgth = 0;
    CSVFile bossCSV = loadCSV("./boss/boss.csv");
    
    void algorithm(){
        /// Système de sauvegarde à X joueurs
        /// Système d'inventaire
        String bossSelected = readString();
        Boss boss = creerBoss(bossSelected);
        printBoss(boss, initPrintBoss(bossSelected));
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
            if (nom != getCell(bossCSV, i, 2)) {
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
}
