package Model.Interfaces;

import org.json.JSONObject;

public interface IJson {
    public JSONObject toJSON(Object value);

    public void fromJSON(JSONObject jsonObject);
}
