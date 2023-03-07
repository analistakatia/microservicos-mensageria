package projetocartoes.microserviceavaliadorcredito.application.exception;

public class ErroSolicitacaoCartaoException extends RuntimeException{
    public ErroSolicitacaoCartaoException(String message){
        super(message);
    }
}
