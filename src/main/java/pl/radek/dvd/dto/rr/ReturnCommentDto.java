package pl.radek.dvd.dto.rr;

public class ReturnCommentDto {
    private int clientId;
    private int movieCopyId;
    private int registryId;
    private String comment;

    public ReturnCommentDto() {
    }

    public ReturnCommentDto(String comment, int movieCopyId, int clientId, int registryId) {
        this.comment = comment;
        this.movieCopyId = movieCopyId;
        this.clientId = clientId;
        this.registryId = registryId;
    }

    public ReturnCommentDto(int movieCopyId, int clientId, int registryId) {
        this.movieCopyId = movieCopyId;
        this.clientId = clientId;
        this.registryId = registryId;
    }

    public ReturnCommentDto(int clientId, int registryId) {
        this.clientId = clientId;
        this.registryId = registryId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMovieCopyId() {
        return movieCopyId;
    }

    public void setMovieCopyId(int movieCopyId) {
        this.movieCopyId = movieCopyId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getRegistryId() {
        return registryId;
    }

    public void setRegistryId(int registryId) {
        this.registryId = registryId;
    }
}
