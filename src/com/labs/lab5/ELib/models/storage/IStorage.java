package com.labs.lab5.ELib.models.storage;

interface IItem {
    static IItem parse(string itemInStr) {
        
    };
}

/**
 * Интерфейс лямбда выражения для фильтрации данных
 * @param <T> обрабатываемый класс данных
 */
@FunctionalInterface
interface IFilter<T> {
    /**
     * Возвращает true если переданный экземпляр класса удовлетворяет нужному условию
     * @param item экземпляр обрататываемого класса
     * @return true если переданный экземпляр класса удовлетворяет нужному условию
     */
    boolean filter(T item);
}

/**
 * Интерфейс хранения данных
 * Обеспечивает загрузку, получение, добавление... данных
 * @param <T> класс хранимых данных
 */
public interface IStorage<T> {
    /**
     * Добавляет элемент в хранилище
     * @param item добавляемый элемент
     * @return удалось ли добавить элемент
     */
    boolean add(T item);

    /**
     * Добавлет переданные элементы в хранилище
     * @param items добавляемые элементы
     * @return удалось ли добавить элементы
     */
    boolean addAll(T[] items);

    /**
     * @return данных в виде массива
     */
    T[] getArrOfData();

    /**
     * Возвращает данные (в виде массива), что удовлетворяют фильтр
     * @param filter лямбда выражение для фильтрации данных
     * @return данные (в виде массива), что удовлетворяют фильтр
     */
    T[] getArrOfData(IFilter<T> filter);
}
