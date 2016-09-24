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
 * @param <T>
 * @param <PK>
 */
public interface GenericDAOHbm<T, PK extends Serializable> {
    boolean create(T t);
    T read(PK pk);
    List<T> read();
    boolean update(T t);
    boolean delete(T t);
}
