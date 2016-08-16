/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.commandexecutor;

/**
 * コマンド実行結果情報を保持する
 *
 * @author normal
 */
public class CommandResult {

    private final String standardOutput;
    private final String standardError;
    private final int returnCode;

    public CommandResult(String standardOutput, String standardError, int returnCode) {
        this.standardOutput = standardOutput;
        this.standardError = standardError;
        this.returnCode = returnCode;
    }

    /**
     *
     * @author normal
     * @return コマンド実行結果のうち、標準出力に送られたもの。
     */
    public String getStandardOutput() {
        return standardOutput;
    }

    /**
     *
     * @author normal
     * @return コマンド実行結果のうち、標準エラー出力に送られたもの。
     */
    public String getStandardError() {
        return standardError;
    }

    /**
     *
     * @author normal
     * @return リターンコード
     */
    public int getReturnCode() {
        return returnCode;
    }

    @Override
    public String toString() {
        String s1;
        if (this.getStandardOutput() != null) {
            s1 = "標準出力=" + this.getStandardOutput();
        } else {
            s1 = "標準出力= NULL";
        }
        String s2;
        if (this.getStandardError() != null) {
            s2 = ", エラー出力=" + this.getStandardError();
        } else {
            s2 = "エラー出力= NULL";
        }
        return s1 + s2 + ", リターンコード=" + this.getReturnCode();
    }
}
