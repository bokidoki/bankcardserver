package com.keepsafe.bankcardserver.utils

import com.google.gson.Gson
import com.keepsafe.bankcardserver.data.dto.UserDTO
import com.keepsafe.bankcardserver.utils.HexConverter.hexStringToByteArray
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

/**
 * JWT 工具类
 * 用于生成、解析和验证JWT令牌
 */
@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration}")
    private var expiration: Long = 0

    private val gson = Gson()

    /**
     * 生成密钥
     */
    private fun getSigningKey(): SecretKey {
        return Keys.hmacShaKeyFor(secret.hexStringToByteArray())
    }

    /**
     * 生成JWT令牌
     * @param user 用户信息对象
     * @return JWT字符串
     */
    fun generateToken(user: UserDTO): String {
        val userInfoJson = gson.toJson(user)

        return Jwts.builder()
            .subject(user.id.toString())
            .claim("userInfo", userInfoJson)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey(), Jwts.SIG.HS256)
            .compact()
    }

    /**
     * 从JWT中提取并反序列化用户对象
     * @param token JWT 字符串
     * @return UserInfo 对象
     */
    fun getUserInfoFromToken(token: String): UserDTO {
        val claims = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .payload

        val userInfo = claims["userInfo"] as String? ?: throw JwtException("userInfo claim is not found")
        return gson.fromJson(userInfo, UserDTO::class.java)
    }

    /**
     * 检查token的合法性
     */
    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token)
            true
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    /**
     * 检查token是否过期
     */
    fun isTokenExpired(token: String): Boolean {
        val claims = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .payload
        return claims.expiration.before(Date())
    }

}