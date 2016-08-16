/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epgtools.reserve3.dialog.Inputprocessor;


/**
 * 入力されたデータの変換処理を実装する。
 *
 * @author dosdiaopfhj
 * @param <T> 文字列を元にして作ったオブジェクトの型。
 */
public interface PostProcessor<T> {

    /**
     * 入力されたデータの変換処理を実装する。
     *
     * @param s 入力された文字列。
     * @return 文字列を元にして作ったオブジェクト。
     * @throws IllegalArgumentException
     * 戻り値とすべきオブジェクトの生成に必要な手段が存在しているが、入力された文字列がオブジェクトの生成条件に当てはまらない場合に投げる。
     * 上位メソッドは再入力を要求する。
     * @throws Dialog.InputProcessor.GenerationInabilityException
     * 何らかの理由でオブジェクトが生成できない場合に投げる。上位メソッドは入力処理を中断し、この例外を更に呼び出し元に投げる。
     */
    abstract T process(String s) throws IllegalArgumentException, GenerationInabilityException;
}
