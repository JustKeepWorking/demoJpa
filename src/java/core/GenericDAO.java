/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hai Nguyen
 */
interface GenericDAO<T, PK extends Serializable> {
    T create(T t);
    T read(PK pk);
    List<T> read();
    T update(T t);
    void delete(T t);
}
