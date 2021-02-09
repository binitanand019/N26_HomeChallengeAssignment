package petStoreBase.framework.enums;

public enum TEST_STATUS {

    RUNNING("running"),
    ENDED("ended"),
    ABORTED("aborted"),
    FAILURE("failure");

    private String status;

    TEST_STATUS(String status){
        this.status = status;
    }

    public static TEST_STATUS getStatus(String status){
        for(TEST_STATUS teststatus:TEST_STATUS.values()){
            if(teststatus.toString().equals(status)){
                return teststatus;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return status;
    }

}
