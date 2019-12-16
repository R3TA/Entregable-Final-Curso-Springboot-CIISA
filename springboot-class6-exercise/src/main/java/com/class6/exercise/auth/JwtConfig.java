package com.class6.exercise.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEA8FXjAZkkc+hipzfSau5pxE0ueLTfOoJc81m3ZOiB93tlOpqv\r\n" + 
			"w6BxrYtvyJDrpueLYwLL1wXPlZZiqyatv+DxDR6dWeuSuknihrZ2IVVAjtRFrE6f\r\n" + 
			"4gDxBfoKxJTiajFieedAxUMo/l1Pd5m87terjjDN7kMLfqSA4tPNYJlInDCqhP0C\r\n" + 
			"i/olFn0VDEXs5z1EnxVUII23CygT9BPHrCAFvMev3FVf4Uj57L4k9GR64Uv+KcVB\r\n" + 
			"FrhbD0m6Aiv5rBCp0pipqoqQMJ64bhUhyJI+OMa5nami6iI0Yw2GOVDTNmq8CXR+\r\n" + 
			"j6U7ZVqprhuVTh154HoMFLozXeZ/4jSBuBkVcwIDAQABAoIBADDnyUHR//D2IN0W\r\n" + 
			"JuNLkIo5inNVmvc0/+UlJISMK6ZIndjHEW0lgPxnm1+c3dcg1uRW9VPUAuHVsE5T\r\n" + 
			"mJerFmItEKfzn+/4iPgQuQkfZ/uohmUButcqieFFeFt4UHp7giZ8QFnjyLu6ESmt\r\n" + 
			"RxzzFh3hIIaTbgtfPjrqDqjRiEz/1Tgk8N+pLDNTaQQC6J+By70+XUzWrJ9GQYg1\r\n" + 
			"q73R7GyChWurU5vfTv1yg0tSIQQjgweFxOq6X3qg9tHH9tvfNohKNHy4khJI7g+R\r\n" + 
			"37dRYoD1MDRxNjKCQPgJxZxl/O5CMDvhChhKs3mdZr0+3p/4WPvqcqd2NoWRGgDE\r\n" + 
			"F543X4ECgYEA/kN88uFJQsY/IcY+p5tPXh2kY7X2A0BuC7KeVHG7Z2vTSXLkd/ra\r\n" + 
			"X2G3iCOn7S1Ek/frW/ydqTZjbJhFaiKC0o430VKEEyRIUfT7O1gm1YaWN4CbBcaT\r\n" + 
			"8udCe91mf612lxhwfk7umrbkt5D77FhizHXijl2RJwqBio9IwTGURkkCgYEA8foM\r\n" + 
			"jrvZ92tuZRRZpf+2Zksw2hZcPjJ7+YrhVV5fge+crMgGfwcWF8Op/vzcQlV/Nv93\r\n" + 
			"EI1yzRYqTkhK1ENBn9aP5i+KHtr78Pui5c1Oz2ORY15L/amc7oMcoYGf0ddKequT\r\n" + 
			"7y5YuXoTp5T0ICbkH2+/SwA+IDebBd+gfug1TdsCgYBakFE99W5xwUkVT5Zbfp87\r\n" + 
			"Z5V5CoPQzq8jrYBllYjpomdEfw7PiWMknBqR/BlBCmGlzqVfQ1AoyD2LnYUD87zj\r\n" + 
			"IFLTVO/EHaVAw+CEV7alUWXJ6R3OZdemqiZtFgP/MBrQYJ0Y8CZepww6MgL5QNYX\r\n" + 
			"+1kx2jPsUvqnDrgcT/t/OQKBgQCwcJ61eR5fPfO6OpN/6ixq/Iq7izBpndAuTmku\r\n" + 
			"IWlMypYYmqW5/EXMwDx1lJVavRLM3IxKAoniJzZj7Gy7x+rTDNXSh3mUNCMfPa0z\r\n" + 
			"GXhM2pJJvZl1djba57l5vpX1oaFKP0/YSJgjbnNkb8VuqE0Q6x+2JZAnV8RvDPfv\r\n" + 
			"cYxknwKBgQD1htfAbJmqQL4C6FCdPVsEcA7SlCkPkWGLGmZxBwf94daiuzOT9CnG\r\n" + 
			"yseKm5KSiFWCwcDAiydlnF19oyWLXyS86hSOEjiFmq1OXj1vOTS9tO9vpbJ2sr5i\r\n" + 
			"8bZwJONhAsmpbHZha7WGSGmAbit0xd/nMxkhf+zGbHVfLY8kbKAuKg==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8FXjAZkkc+hipzfSau5p\r\n" + 
			"xE0ueLTfOoJc81m3ZOiB93tlOpqvw6BxrYtvyJDrpueLYwLL1wXPlZZiqyatv+Dx\r\n" + 
			"DR6dWeuSuknihrZ2IVVAjtRFrE6f4gDxBfoKxJTiajFieedAxUMo/l1Pd5m87ter\r\n" + 
			"jjDN7kMLfqSA4tPNYJlInDCqhP0Ci/olFn0VDEXs5z1EnxVUII23CygT9BPHrCAF\r\n" + 
			"vMev3FVf4Uj57L4k9GR64Uv+KcVBFrhbD0m6Aiv5rBCp0pipqoqQMJ64bhUhyJI+\r\n" + 
			"OMa5nami6iI0Yw2GOVDTNmq8CXR+j6U7ZVqprhuVTh154HoMFLozXeZ/4jSBuBkV\r\n" + 
			"cwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}
