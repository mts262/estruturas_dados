package br.edu.ifba.vca.bsi.ed.linkedlistdao.dao.repository;

public class OverflowException extends RuntimeException{
    public OverflowException(String message) {
        super(message);
    }
}
