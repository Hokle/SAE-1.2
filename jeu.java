class jeu extends Program{
    
    void algorithm(){
        /// Système de sauvegarde à X joueurs
        /// Système d'inventaire
        int int1 = readInt();
        char operateur = readChar();
        int int2 = readInt();
        Operation test = creerOperation(int1, operateur, int2);
        printOperation(test);
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

    Boss creerBoss(int pv, int force, String nom, String[] design) {
        Boss newBoss = new Boss();
        newBoss.pv = pv;
        newBoss.force = force;
        newBoss.nom = nom;
        newBoss.design = design;
        return newBoss;
    }

    void printBoss(Boss boss) {
        for (int i = 0 ; i < length(boss.design) ; i++) {
            println(boss.design[i]);
        }
        println();
    }
}