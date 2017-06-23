import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestTxHandler {

    private final UTXOPool uPool;

    public TestTxHandler() throws IOException {
        String skpFile = "files/SampleKeyPairs.txt";
        String supFile = "files/SampleUTXOPool.txt";
        SampleKeyPairs skp = SampleKeyPairsFileHandler.readKeyPairsFromFile(skpFile);
        SampleUTXOPool sup = SampleUTXOPoolFileHandler.readSampleUTXOPoolFromFile(skp, supFile);

        uPool = sup.getPool();
    }

    private static boolean verify(Transaction[] allTxs1, UTXOPool uPool) {
        Transaction[] copyTxs1 = new Transaction[allTxs1.length];
        for (int i = 0; i < copyTxs1.length; i++)
            copyTxs1[i] = allTxs1[i];

        TxHandler student1 = new TxHandler(new UTXOPool(uPool));
        TxHandlerVerifier verifier1 = new TxHandlerVerifier(uPool);

        System.out.println("Total Transactions = " + allTxs1.length);
        Transaction[] stx1 = student1.handleTxs(copyTxs1);
        System.out.println("Number of transactions returned valid by student = " + stx1.length);
        boolean passed1 = verifier1.check(allTxs1, stx1);

        return passed1;
    }

    private static boolean verify(Transaction[] allTxs1, Transaction[] allTxs2, UTXOPool uPool) {
        Transaction[] copyTxs1 = new Transaction[allTxs1.length];
        for (int i = 0; i < copyTxs1.length; i++)
            copyTxs1[i] = allTxs1[i];

        Transaction[] copyTxs2 = new Transaction[allTxs2.length];
        for (int i = 0; i < copyTxs2.length; i++)
            copyTxs2[i] = allTxs2[i];

        TxHandler student1 = new TxHandler(new UTXOPool(uPool));
        TxHandlerVerifier verifier1 = new TxHandlerVerifier(uPool);

        TxHandler student2 = new TxHandler(new UTXOPool(uPool));
        TxHandlerVerifier verifier2 = new TxHandlerVerifier(uPool);

        System.out.println("Total Transactions = " + allTxs1.length);
        Transaction[] stx1 = student1.handleTxs(copyTxs1);
        System.out.println("Number of transactions returned valid by student = " + stx1.length);
        boolean passed1 = verifier1.check(allTxs1, stx1);

        System.out.println("Total Transactions = " + allTxs2.length);
        Transaction[] stx2 = student2.handleTxs(copyTxs2);
        System.out.println("Number of transactions returned valid by student = " + stx2.length);
        boolean passed2 = verifier2.check(allTxs2, stx2);

        return passed1 && passed2;
    }

    private static boolean verify(Transaction[] allTxs1, Transaction[] allTxs2,
                                  Transaction[] allTxs3, UTXOPool uPool) {
        Transaction[] copyTxs1 = new Transaction[allTxs1.length];
        for (int i = 0; i < copyTxs1.length; i++)
            copyTxs1[i] = allTxs1[i];

        Transaction[] copyTxs2 = new Transaction[allTxs2.length];
        for (int i = 0; i < copyTxs2.length; i++)
            copyTxs2[i] = allTxs2[i];

        Transaction[] copyTxs3 = new Transaction[allTxs3.length];
        for (int i = 0; i < copyTxs3.length; i++)
            copyTxs3[i] = allTxs3[i];

        TxHandler student1 = new TxHandler(new UTXOPool(uPool));
        TxHandlerVerifier verifier1 = new TxHandlerVerifier(uPool);

        TxHandler student2 = new TxHandler(new UTXOPool(uPool));
        TxHandlerVerifier verifier2 = new TxHandlerVerifier(uPool);

        TxHandler student3 = new TxHandler(new UTXOPool(uPool));
        TxHandlerVerifier verifier3 = new TxHandlerVerifier(uPool);

        System.out.println("Total Transactions = " + allTxs1.length);
        Transaction[] stx1 = student1.handleTxs(copyTxs1);
        System.out.println("Number of transactions returned valid by student = " + stx1.length);
        boolean passed1 = verifier1.check(allTxs1, stx1);

        System.out.println("Total Transactions = " + allTxs2.length);
        Transaction[] stx2 = student2.handleTxs(copyTxs2);
        System.out.println("Number of transactions returned valid by student = " + stx2.length);
        boolean passed2 = verifier2.check(allTxs2, stx2);

        System.out.println("Total Transactions = " + allTxs3.length);
        Transaction[] stx3 = student3.handleTxs(copyTxs3);
        System.out.println("Number of transactions returned valid by student = " + stx3.length);
        boolean passed3 = verifier3.check(allTxs3, stx3);

        return passed1 && passed2 && passed3;
    }

    private static boolean verifyPoolUpdate(Transaction[] allTxs1, Transaction[] allTxs2,
                                            Transaction[] allTxs3, UTXOPool uPool) {
        Transaction[] copyTxs1 = new Transaction[allTxs1.length];
        for (int i = 0; i < copyTxs1.length; i++)
            copyTxs1[i] = allTxs1[i];

        Transaction[] copyTxs2 = new Transaction[allTxs2.length];
        for (int i = 0; i < copyTxs2.length; i++)
            copyTxs2[i] = allTxs2[i];

        Transaction[] copyTxs3 = new Transaction[allTxs3.length];
        for (int i = 0; i < copyTxs3.length; i++)
            copyTxs3[i] = allTxs3[i];

        TxHandler student = new TxHandler(new UTXOPool(uPool));
        TxHandlerVerifier verifier = new TxHandlerVerifier(uPool);

        System.out.println("Total Transactions = " + allTxs1.length);
        Transaction[] stx1 = student.handleTxs(copyTxs1);
        System.out.println("Number of transactions returned valid by student = " + stx1.length);
        boolean passed1 = verifier.check(allTxs1, stx1);

        System.out.println("Total Transactions = " + allTxs2.length);
        Transaction[] stx2 = student.handleTxs(copyTxs2);
        System.out.println("Number of transactions returned valid by student = " + stx2.length);
        boolean passed2 = verifier.check(allTxs2, stx2);

        System.out.println("Total Transactions = " + allTxs3.length);
        Transaction[] stx3 = student.handleTxs(copyTxs3);
        System.out.println("Number of transactions returned valid by student = " + stx3.length);
        boolean passed3 = verifier.check(allTxs3, stx3);

        return passed1 && passed2 && passed3;
    }

    // all transactions are simple and valid
    @Test
    public void test1() throws FileNotFoundException, IOException {
        System.out.println("Test 1: test handleTransactions() with simple and valid transactions");

        String common = "files/SampleTxsTest1-";
        String file1 = common + "1.txt";
        String file2 = common + "2.txt";
        String file3 = common + "3.txt";
        Transaction[] allTxs1 = TransactionsArrayFileHandler.readTransactionsFromFile(file1);
        Transaction[] allTxs2 = TransactionsArrayFileHandler.readTransactionsFromFile(file2);
        Transaction[] allTxs3 = TransactionsArrayFileHandler.readTransactionsFromFile(file3);

        assertTrue("Test 1: test handleTransactions() with simple and valid transactions", verify(allTxs1, allTxs2, allTxs3, uPool));
    }

    // all transactions are simple and valid
    @Test
    public void test2() throws FileNotFoundException, IOException {
        System.out.println("Test 2: test handleTransactions() with simple but "
                + "some invalid transactions because of invalid signatures");

        String common = "files/SampleTxsTest2-";
        String file1 = common + "1.txt";
        String file2 = common + "2.txt";
        String file3 = common + "3.txt";
        Transaction[] allTxs1 = TransactionsArrayFileHandler.readTransactionsFromFile(file1);
        Transaction[] allTxs2 = TransactionsArrayFileHandler.readTransactionsFromFile(file2);
        Transaction[] allTxs3 = TransactionsArrayFileHandler.readTransactionsFromFile(file3);

        assertTrue("Test 2: test handleTransactions() with simple but "
                + "some invalid transactions because of invalid signatures", verify(allTxs1, allTxs2, allTxs3, uPool));
    }

    // all transactions are simple and valid
    @Test
    public void test3() throws FileNotFoundException, IOException {
        System.out.println("Test 3: test handleTransactions() with simple but "
                + "some invalid transactions because of inputSum < outputSum");

        String common = "files/SampleTxsTest3-";
        String file1 = common + "1.txt";
        String file2 = common + "2.txt";
        String file3 = common + "3.txt";
        Transaction[] allTxs1 = TransactionsArrayFileHandler.readTransactionsFromFile(file1);
        Transaction[] allTxs2 = TransactionsArrayFileHandler.readTransactionsFromFile(file2);
        Transaction[] allTxs3 = TransactionsArrayFileHandler.readTransactionsFromFile(file3);

        assertTrue("Test 3: test handleTransactions() with simple but "
                + "some invalid transactions because of inputSum < outputSum", verify(allTxs1, allTxs2, allTxs3, uPool));
    }

    // all transactions are simple and valid
    @Test
    public void test4() throws FileNotFoundException, IOException {
        System.out.println("Test 4: test handleTransactions() with simple and "
                + "valid transactions with some double spends");

        String common = "files/SampleTxsTest4-";
        String file1 = common + "1.txt";
        String file2 = common + "2.txt";
        String file3 = common + "3.txt";
        Transaction[] allTxs1 = TransactionsArrayFileHandler.readTransactionsFromFile(file1);
        Transaction[] allTxs2 = TransactionsArrayFileHandler.readTransactionsFromFile(file2);
        Transaction[] allTxs3 = TransactionsArrayFileHandler.readTransactionsFromFile(file3);

        assertTrue("Test 4: test handleTransactions() with simple and "
                + "valid transactions with some double spends", verify(allTxs1, allTxs2, allTxs3, uPool));
    }

    // all transactions are simple and valid
    @Test
    public void test5() throws FileNotFoundException, IOException {
        System.out.println("Test 5: test handleTransactions() with valid but "
                + "some transactions are simple, some depend on other transactions");

        String common = "files/SampleTxsTest5-";
        String file1 = common + "1.txt";
        String file2 = common + "2.txt";
        String file3 = common + "3.txt";
        Transaction[] allTxs1 = TransactionsArrayFileHandler.readTransactionsFromFile(file1);
        Transaction[] allTxs2 = TransactionsArrayFileHandler.readTransactionsFromFile(file2);
        Transaction[] allTxs3 = TransactionsArrayFileHandler.readTransactionsFromFile(file3);

        assertTrue("Test 5: test handleTransactions() with valid but "
                + "some transactions are simple, some depend on other transactions", verify(allTxs1, allTxs2, allTxs3, uPool));
    }

    // all transactions are simple and valid
    @Test
    public void test6() throws FileNotFoundException, IOException {
        System.out.println("Test 6: test handleTransactions() with valid and simple but "
                + "some transactions take inputs from non-exisiting utxo's");

        String common = "files/SampleTxsTest6-";
        String file1 = common + "1.txt";
        String file2 = common + "2.txt";
        String file3 = common + "3.txt";
        Transaction[] allTxs1 = TransactionsArrayFileHandler.readTransactionsFromFile(file1);
        Transaction[] allTxs2 = TransactionsArrayFileHandler.readTransactionsFromFile(file2);
        Transaction[] allTxs3 = TransactionsArrayFileHandler.readTransactionsFromFile(file3);

        assertTrue("Test 6: test handleTransactions() with valid and simple but "
                + "some transactions take inputs from non-exisiting utxo's", verify(allTxs1, allTxs2, allTxs3, uPool));
    }

    // all transactions are simple and valid
    @Test
    public void test7() throws FileNotFoundException, IOException {
        System.out.println("Test 7: test handleTransactions() with complex Transactions");

        String common = "files/SampleTxsTest7-";
        String file1 = common + "1.txt";
        String file2 = common + "2.txt";
        String file3 = common + "3.txt";
        Transaction[] allTxs1 = TransactionsArrayFileHandler.readTransactionsFromFile(file1);
        Transaction[] allTxs2 = TransactionsArrayFileHandler.readTransactionsFromFile(file2);
        Transaction[] allTxs3 = TransactionsArrayFileHandler.readTransactionsFromFile(file3);

        assertTrue("Test 7: test handleTransactions() with complex Transactions", verify(allTxs1, allTxs2, allTxs3, uPool));
    }

    // all transactions are simple and valid
    @Test
    public void test8() throws FileNotFoundException, IOException {
        System.out.println("Test 8: test handleTransactions() with simple, valid transactions "
                + "being called again to check for changes made in the pool");

        String common = "files/SampleTxsTest8-";
        String file1 = common + "1.txt";
        String file2 = common + "2.txt";
        String file3 = common + "3.txt";
        Transaction[] allTxs1 = TransactionsArrayFileHandler.readTransactionsFromFile(file1);
        Transaction[] allTxs2 = TransactionsArrayFileHandler.readTransactionsFromFile(file2);
        Transaction[] allTxs3 = TransactionsArrayFileHandler.readTransactionsFromFile(file3);

        assertTrue("Test 8: test handleTransactions() with simple, valid transactions "
                + "being called again to check for changes made in the pool", verifyPoolUpdate(allTxs1, allTxs2, allTxs3, uPool));
    }

}
