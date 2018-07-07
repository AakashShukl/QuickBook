package pay4free.in.quickbook.model;

import java.util.List;

/**
 * Created by AAKASH on 09-03-2018.
 */

public class MyResponse {
    public long multicast_id;
    public int success;
    public int failure;
    public int canonical_ids;
    public List<Result> results;
}
