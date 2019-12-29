package carzanodev.genuniv.microservices.common.util;

public enum MetaMessage {

    SIMPLE_MSG("Successful query!"),
    LIST_MSG("Query returned %s results!"),
    CREATE_MSG("Successfully inserted data with id = %s!"),
    MUL_CREATE_MSG("Successfully inserted %d data!"),
    RETRIEVE_MSG("Successfully retrieved data with id = %s!"),
    UPDATE_MSG("Successfully updated data with id = %s!"),
    DELETE_MSG("Successfully deleted data with id = %s!");

    private final String msg;

    MetaMessage(String msg) {
        this.msg = msg;
    }

    public String make(String fill) {
        return String.format(msg, fill);
    }

    public String make(Object fill) {
        return make(String.valueOf(fill));
    }

    public String make(int fill) {
        return make(String.valueOf(fill));
    }

    public String make(long fill) {
        return make(String.valueOf(fill));
    }

    public String make(float fill) {
        return make(String.valueOf(fill));
    }

    public String make(double fill) {
        return make(String.valueOf(fill));
    }

}
