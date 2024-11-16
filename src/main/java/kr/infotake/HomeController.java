package kr.infotake;

import java.util.Base64;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author dev
 */
@Controller
public class HomeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public ResponseEntity<String> homeRoot(Locale locale, Model model, HttpServletRequest request) {
		
		String sql = "SELECT * FROM user WHERE user = ?";
		jdbcTemplate.queryForList(sql, "root");
		// 쿼리가 실행된 후, 연결은 자동으로 반환되므로 명시적으로 Connection.close()를 호출할 필요가 없습니다.
		
		
		String encryptionKey = "your_encryption_key"; // 암호화/복호화 키

        // 1. 암호화
        String sqlEncrypt = "SELECT AES_ENCRYPT('your_data', ?)";
        byte[] encryptedData = jdbcTemplate.queryForObject(sqlEncrypt, byte[].class, encryptionKey);
        
        // 암호화된 데이터를 Base64로 변환하여 출력 (디버깅용)
        String encryptedString = Base64.getEncoder().encodeToString(encryptedData);
        System.out.println("Encrypted Data: " + encryptedString);

        // 2. 복호화
        String sqlDecrypt = "SELECT AES_DECRYPT(?, ?)";
        byte[] decryptedData = jdbcTemplate.queryForObject(sqlDecrypt, byte[].class, encryptedData, encryptionKey);

        // 복호화된 데이터를 문자열로 변환
        String decryptedString = new String(decryptedData);
        System.out.println("Decrypted Data: " + decryptedString);
		
		// HTML 콘텐츠를 ResponseEntity로 반환
		String htmlContent = String.format("Welcome home! The client locale is {}.", locale);
		return ResponseEntity.ok().body(htmlContent);
	}
}
