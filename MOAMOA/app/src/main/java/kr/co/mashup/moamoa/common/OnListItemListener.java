package kr.co.mashup.moamoa.common;


public interface OnListItemListener<T> {
    void onListItemClick(T item);

    void onListITemDelete(int position);
}
