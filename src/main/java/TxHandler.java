import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TxHandler {

    private final UTXOPool utxoPool;

    /* Creates a public ledger whose current UTXOPool (collection of unspent
         * transaction outputs) is utxoPool. This should make a defensive copy of
         * utxoPool by using the UTXOPool(UTXOPool uPool) constructor.
         */
    public TxHandler(UTXOPool utxoPool) {
        // IMPLEMENT THIS
        this.utxoPool = utxoPool;
    }

	/* Returns true if 
     * (1) all outputs claimed by tx inputs are in the current UTXO pool,
	 * (2) the signatures on each input of tx are valid, 
	 * (3) no UTXO is claimed multiple times by tx, 
	 * (4) all of tx’s output values are non-negative, and
	 * (5) the sum of tx’s input values is greater than or equal to the sum of   
	        its output values;
	   and false otherwise.
	 */

    public boolean isValidTx(Transaction tx) {
        return allOutputsAreInPool(tx)
                && allInputSignaturesAreValid(tx)
                && noUTXOisClaimedMultipleTimes(tx)
                && allSumsAreValid(tx);
    }

    private boolean allSumsAreValid(Transaction tx) {
        if (tx.getOutputs().stream().anyMatch(out -> out.value < 0)) {
            return false;
        }
        double totalInput = tx.getInputs().stream().mapToDouble(in -> utxoPool.getTxOutput(in.buildUTXO()).value).sum();
        double totalOutput = tx.getOutputs().stream().mapToDouble(out -> out.value).sum();
        return totalInput >= totalOutput;
    }

    private boolean noUTXOisClaimedMultipleTimes(Transaction tx) {
        Set<UTXO> spent = new HashSet<>();
        for (Transaction.Input input : tx.getInputs()) {
            UTXO utxo = input.buildUTXO();
            if (spent.contains(utxo)) {
                return false;
            }
            spent.add(utxo);
        }
        return true;
    }

    private boolean allInputSignaturesAreValid(Transaction tx) {
        for (int i = 0; i < tx.getInputs().size(); i++) {
            Transaction.Input input = tx.getInput(i);
            Transaction.Output txOutput = utxoPool.getTxOutput(input.buildUTXO());
            if (!txOutput.address.verifySignature(tx.getRawDataToSign(i), input.signature)) {
                return false;
            }
        }
        return true;
    }

    private boolean allOutputsAreInPool(Transaction tx) {
        for (Transaction.Input input : tx.getInputs()) {
            if (!utxoPool.contains(input.buildUTXO())) {
                return false;
            }
        }
        return true;
    }

    /* Handles each epoch by receiving an unordered array of proposed
     * transactions, checking each transaction for correctness,
     * returning a mutually valid array of accepted transactions,
     * and updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        ArrayList<Transaction> validTransactions = new ArrayList<>();

        for (int i = 0; i < possibleTxs.length; i++) {
            Transaction tx = possibleTxs[i];
            if (isValidTx(tx)) {
                validTransactions.add(tx);
                updatePool(tx);
            }
        }


        Transaction[] target = new Transaction[validTransactions.size()];
        return validTransactions.toArray(target);
    }

    private void updatePool(Transaction tx) {
        for (Transaction.Input input : tx.getInputs()) {
            utxoPool.removeUTXO(input.buildUTXO());
        }
        for (int o = 0; o < tx.getOutputs().size(); o++) {
            utxoPool.addUTXO(new UTXO(tx.getHash(), o), tx.getOutput(o));
        }
    }

} 
