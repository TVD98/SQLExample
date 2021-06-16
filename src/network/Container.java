/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.function.Supplier;

/**
 *
 * @author GMT
 * @param <T>
 */
public class Container<T extends Decoder>{
    private Supplier<T> supplier;

    public Container(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    T createContents() {
        return supplier.get();
    }
}
