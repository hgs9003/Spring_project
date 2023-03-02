package com.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

// 컨트롤러에서 호출할 목적으로 생성.
public class FileUtils {
	//상품이미지파일에 대한 업로드작업
	/*
	 1.날짜폴더 생성해서 파일관리
	 2.Thumnail 이미지생성 : 이미지파일
	 3. 일반파일과 이미지파일 구분작업
	 4. byte[]배열로 다운로드. 
	 */
	
	//파일업로드 작업.   C:\\doccomsa\\upload\\goods\\2022\\11\\22
	public static String uploadFile(String uploadFolder, String uploadDateFolderPath, MultipartFile uploadFile) {
		
		String uploadFileName = ""; // 실제 업로드한 파일명.
		
		// File클래스 : 파일과 폴더 작업을 하는 목적
				
		// 폴더정보
		File uploadPath = new File(uploadFolder, uploadDateFolderPath);
		
		//폴더 존재여부
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();  //존재 하지않으면, 하위(자손)폴더까지 한번에 생성됨.
		}
		
		//클라이언트에서 업로드한 파일명.
		String uploadClientFileName = uploadFile.getOriginalFilename();
		
		//중복되지 않는 고유의 문자열 생성
		UUID uuid = UUID.randomUUID();
		
		//업로드시 중복되지 않는 파일이름을 생성
		uploadFileName = uuid.toString() + "_" + uploadClientFileName;
		
		try {
			// 파일정보
			File saveFile = new File(uploadPath, uploadFileName);
			uploadFile.transferTo(saveFile);  // 서버의 폴더에 파일복사.(업로드)
			
			//Thumnail 작업 ? 원본이미지를 대상으로 사본이미지를 해상도의 손실을 줄이고, 크기를 작게 만드는 것.
			if(checkImageType(saveFile)) {
				
				// 출력스트림은 객체만 생성이 되어도, 실제 경로에 파일이 생성되어 있다.
				// 파일생성위치:  C:\\doccomsa\\upload\\goods\\2022\\11\\22\\abc.gif
				FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
				
				System.out.println("파일명: " + uploadFile.getName());
				
				//생성된 파일크기 : 0byte. 원본이미지의 파일을 입력스트림을 통하여 읽고, 너비 100, 높이 100 크기인 사본파일을 생성한다.
				Thumbnailator.createThumbnail(uploadFile.getInputStream(), thumbnail, 100, 100);
				thumbnail.close();
				
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return uploadFileName;
		
	}
	
	
	//날짜폴더 생성하기위한 날짜형태의 문자열(운영체제 경로의 구분자)
	public static String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date); // "2022-11-23"
		
		//1)리눅스 또는 맥은 구분자 / : "2022-11-22" -> "2022/11/22"
		//2)윈도우즈 \ : "2022-11-22" -> "2022\11\22"
		
		return str.replace("-", File.separator);
	}
	
	// 일반파일 또는 이미지파일 구분
	private static boolean checkImageType(File saveFile) {
		
		boolean isImage = false;
		
		try {
			// MIME : text/html, text/plain, image/jpeg
			String contentType = Files.probeContentType(saveFile.toPath()); 
			
			isImage = contentType.startsWith("image");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return isImage;
		
	}
	
	
	//상품이미지를 바이트배열로 읽어오는 작업
	public static ResponseEntity<byte[]> getFile(String uploadPath, String fileName) throws IOException {
		
		ResponseEntity<byte[]> entity = null;
		
		File file = new File(uploadPath, fileName);
		
		//상품이미지가 존재하지 않을 경우
		if(!file.exists()) {
			return entity;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", Files.probeContentType(file.toPath()));
		
		entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		
		return entity;
	}
	
	//파일삭제
	// uploadPath : "C:\\doccomsa\\upload\\goods\\"
	// fileName : 2022/11/23/,    4877bbfb-ed93-4869-becc-baa68825a364_037007001270.gif
	public static void deleteFile(String uploadPath, String folder, String fileName) {
		
		//원본이미지 삭제
		new File((uploadPath + folder + "/" + fileName).replace('/', File.separatorChar)).delete();
		//썸네일이미지 삭제
		new File((uploadPath + folder + "/" + "s_" + fileName).replace('/', File.separatorChar)).delete();
	}
	
	
	
	
	
}
