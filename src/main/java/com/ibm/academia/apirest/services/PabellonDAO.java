package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>
{
    public Iterable<Pabellon> findPabellonesByDireccionLocalidad(String localidad);
    public Iterable<Pabellon> findPabellonesByNombre(String nombre);
}
