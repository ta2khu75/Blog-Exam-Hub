package ta2khu75.com.webquiz.service;
public interface CrudService <D, T, E>{
    E create(T request);
    E update(D id,T request);
    E read(D id);
    void delete(D id);
}