package cn.mrchen.basic.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JJWTTokenCreator {

    /*
    使用ChronoUnit代替
    public static final Integer HOUR = 0;
    public static final Integer DAY = 1;
    public static final Integer MONTH = 2;
    public static final Integer YEAR = 3;
    */

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private Map<String, Object> claims = new HashMap<String, Object>();
    private ChronoUnit validTimeType = ChronoUnit.DAYS;
    private Integer validTimeLong = 1;
    private static SecretKey key = null;

    private static SecretKey generalKey() {
        if (key == null) {
            // 以后需要完善 stringKey 的获取方式, 通过配置文件获取
            String stringKey = "mrchen";
            byte[] encodedKey = Base64.decodeBase64(stringKey);
            key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        }

        return key;

    }

    /**
     * 生成token
     * @return
     */
    public String createToken() {
        JwtBuilder builder = Jwts.builder();
        Date iat = new Date(System.currentTimeMillis());
        builder.setIssuedAt(iat);
        Date exp = DateHelper.calculateByDays(iat, validTimeLong);
        builder.setHeaderParam("typ", "JWT");
        builder.setHeaderParam("alg", signatureAlgorithm.getValue());
        builder.setExpiration(exp);
        builder.setClaims(claims);
        builder.signWith(signatureAlgorithm, generalKey());
        return builder.compact();
    }

    public static Claims parseToken(String token) {
        if (StringUtils.isEmpty(token))
            return null;
        SecretKey key = generalKey();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        if (signatureAlgorithm != null)
            this.signatureAlgorithm = signatureAlgorithm;
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    public Object getClaim(String key) {
        return this.claims.get(key);
    }

    public void setClaim(String key, Object value) {
        this.claims.put(key, value);
    }

    public ChronoUnit getValidTimeType() {
        return validTimeType;
    }

    public void setValidTimeType(ChronoUnit validTimeType) {
        if (validTimeType != null)
            this.validTimeType = validTimeType;
    }

    public Integer getValidTimeLong() {
        return validTimeLong;
    }

    public void setValidTimeLong(Integer validTimeLong) {
        if (validTimeLong != null && validTimeLong > 0)
            this.validTimeLong = validTimeLong;
    }

    public void setValidTime(ChronoUnit validTimeType, Integer validTimeLong) {
        if (validTimeType != null || (validTimeLong != null && validTimeLong > 0)) {
            this.validTimeType = validTimeType;
            this.validTimeLong = validTimeLong;
        }
    }

}
