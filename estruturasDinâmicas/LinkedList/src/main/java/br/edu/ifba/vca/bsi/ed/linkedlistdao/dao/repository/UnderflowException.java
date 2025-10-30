package br.edu.ifba.vca.bsi.ed.linkedlistdao.dao.repository;

public class UnderflowException extends RuntimeException {
    public UnderflowException(String message) {
        super(message);
    }
}
