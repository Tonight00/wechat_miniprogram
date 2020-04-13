package dao;

import java.math.BigDecimal;
import java.util.Map;

public class User
{
    private String name;
    private BigDecimal assets;
    private BigDecimal rates;
    
    public String getId ()
    {
        return id;
    }
    
    public void setId (String id)
    {
        this.id = id;
    }
    
    public String getTel ()
    {
        return tel;
    }
    
    public void setTel (String tel)
    {
        this.tel = tel;
    }
    
    private BigDecimal interest;
    private String id;
    private String tel;
    
    public String getName ()
    {
        return name;
    }
    
    public BigDecimal getAssets ()
    {
        return assets;
    }
    
    
    @Override
    public String toString ()
    {
        return "User{" + "name='" + name + '\'' + ", assets=" + assets + ", rates=" + rates
                + ", interest=" + interest + ", id='" + id + '\'' + ", tel='" + tel + '\'' + '}';
    }
    
    public void setAssets (BigDecimal assets)
    {
        this.assets = assets;
    }
    
    public void setRates (BigDecimal rates)
    {
        this.rates = rates;
    }
    
    public BigDecimal getRates ()
    {
        return rates;
    }
    
    public BigDecimal getInterest ()
    {
        interest = assets.multiply(rates);
        return interest;
    }
    
    public User () {
    
    }
    
    public void add(String label, Object value){
        switch (label) {
            case "name": this.name = (String)value;break;
            case "assets": this.assets = (BigDecimal) value;break;
            case  "rates": this.rates = (BigDecimal) value;break;
            default:System.out.println("user.add方法出错了！！");
        }
    }
    
    public void  setInfo(Map<String,Object> mp) {
        mp.put("name",name);
        mp.put("assets",assets);
        mp.put("rates",rates);
        mp.put("interest",getInterest());
    }
    
    
}
