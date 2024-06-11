package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.HotelAddressDTO;
import com.Thienbao.booking.dto.HotelDTO;
import com.Thienbao.booking.dto.HotelImageDTO;
import com.Thienbao.booking.dto.HotelReviewsDTO;
import com.Thienbao.booking.entity.*;
import com.Thienbao.booking.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    HotelImageRepository hotelImageRepository;
    @Autowired
    private HotelReviewsRepository hotelReviewsRepository;
    @Autowired
    private HotelAddressRepository hotelAddressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;



    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotelEntities = hotelRepository.findAll();
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        for (Hotel hotelEntity : hotelEntities) {
            HotelDTO hotelDTO = new HotelDTO();

            hotelDTO.setId(hotelEntity.getId());
            hotelDTO.setName(hotelEntity.getName());
            hotelDTO.setCheckInTime(hotelEntity.getCheckInTime());
            hotelDTO.setCheckOutTime(hotelEntity.getCheckOutTime());
            hotelDTO.setCloseTime(hotelEntity.getCloseTime());
            hotelDTO.setOpenTime(hotelEntity.getOpenTime());
            hotelDTO.setDescription(hotelEntity.getDescription());
            hotelDTO.setPhone(hotelEntity.getPhone());
            hotelDTO.setRating(hotelEntity.getRating());


            if(hotelEntity.getHotelAddress() != null){
                HotelAddress addressEntity = hotelEntity.getHotelAddress();

                HotelAddressDTO hotelAddressDTO = new HotelAddressDTO();
                hotelAddressDTO.setStreetName(addressEntity.getStreetName());
                hotelAddressDTO.setStreetNumber(addressEntity.getStreetNumber());
                hotelAddressDTO.setCountry(addressEntity.getCountry());
                hotelAddressDTO.setProvince(addressEntity.getProvince());
                hotelAddressDTO.setDistrict(addressEntity.getDistrict());
                hotelAddressDTO.setCity(addressEntity.getCity());
                hotelDTO.setHotelAddressDTO(hotelAddressDTO);
            }else{
                System.out.println("null");
            }
            List<HotelImage> hotelImageEntities = hotelImageRepository.findByHotelId(hotelEntity);
            List<HotelImageDTO> hotelImageDTOs = new ArrayList<>();

            for (HotelImage hotelImageEntity : hotelImageEntities) {
                HotelImageDTO hotelImageDTO = new HotelImageDTO();
                hotelImageDTO.setId(hotelImageEntity.getId());
                hotelImageDTO.setImageTitle(hotelImageEntity.getImageTitle());
                hotelImageDTO.setImageDescription(hotelImageEntity.getImageDescription());
                hotelImageDTO.setImagePath(hotelImageEntity.getImagePath());
                hotelImageDTO.setUploadDate(hotelImageEntity.getUploadDate());
                hotelImageDTOs.add(hotelImageDTO);
            }

            hotelDTO.setListHotelImageDTO(hotelImageDTOs);


            List<HotelReviews> hotelReviewsEntities = hotelReviewsRepository.findByHotelId(hotelEntity);
            List<HotelReviewsDTO> hotelReviewsDTOs = new ArrayList<>();
            for (HotelReviews hotelReviewsEntity : hotelReviewsEntities) {
                HotelReviewsDTO hotelReviewsDTO = new HotelReviewsDTO();
                hotelReviewsDTO.setId(hotelReviewsEntity.getId());
                hotelReviewsDTO.setComment(hotelReviewsEntity.getComment());
                hotelReviewsDTO.setReviewDate(hotelReviewsEntity.getReviewDate());
                hotelReviewsDTOs.add(hotelReviewsDTO);
            }
            hotelDTO.setListHotelReviewsDTO(hotelReviewsDTOs);


            hotelDTOs.add(hotelDTO);

        }
        return hotelDTOs;
    }
    public HotelDTO addHotel(HotelDTO hotelDTO) {
        // Tạo một đối tượng HotelEntity mới



        Hotel hotelEntity = new Hotel();
        hotelEntity.setName(hotelDTO.getName());
        hotelEntity.setDescription(hotelDTO.getDescription());
        hotelEntity.setCheckInTime(hotelDTO.getCheckInTime());
        hotelEntity.setCheckOutTime(hotelDTO.getCheckOutTime());
        hotelEntity.setCloseTime(hotelDTO.getCloseTime());
        hotelEntity.setOpenTime(hotelDTO.getOpenTime());
        hotelEntity.setPhone(hotelDTO.getPhone());
        hotelEntity.setRating(hotelDTO.getRating());
        hotelEntity.setUserId(hotelDTO.getUserEntity());

        // Lưu đối tượng khách sạn mới
        Hotel savedHotelEntity = hotelRepository.save(hotelEntity);
        User userEntity = userRepository.findById(hotelDTO.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        hotelEntity.setUserId(userEntity);

        if (hotelDTO.getHotelAddressDTO() != null) {
            // Tạo một đối tượng HotelAddressEntity mới
            HotelAddress addressEntity = new HotelAddress();
            addressEntity.setHotelIdFk(savedHotelEntity);
            addressEntity.setStreetName(hotelDTO.getHotelAddressDTO().getStreetName());
            addressEntity.setStreetNumber(hotelDTO.getHotelAddressDTO().getStreetNumber());
            addressEntity.setCountry(hotelDTO.getHotelAddressDTO().getCountry());
            addressEntity.setProvince(hotelDTO.getHotelAddressDTO().getProvince());
            addressEntity.setDistrict(hotelDTO.getHotelAddressDTO().getDistrict());
            addressEntity.setCity(hotelDTO.getHotelAddressDTO().getCity());

            //  Lưu đối tượng địa chỉ khách sạn mới
            hotelAddressRepository.save(addressEntity);
        }
        if (hotelDTO.getListHotelImageDTO() != null) {
            // Lặp qua danh sách DTO hình ảnh của khách sạn
            for (HotelImageDTO imageDTO : hotelDTO.getListHotelImageDTO()) {
                // Tạo một đối tượng HotelImageEntity mới
                HotelImage imageEntity = new HotelImage();
                imageEntity.setHotelId(savedHotelEntity); // Thiết lập quan hệ với hotelEntity
                imageEntity.setImageTitle(imageDTO.getImageTitle());
                imageEntity.setImageDescription(imageDTO.getImageDescription());
                imageEntity.setImagePath(imageDTO.getImagePath());
                imageEntity.setUploadDate(imageDTO.getUploadDate());

                // Lưu đối tượng hình ảnh khách sạn mới
                hotelImageRepository.save(imageEntity);
            }
        }

        return hotelDTO;
    }
    public void deleteHotel(int hotelId) {
        // Kiểm tra xem khách sạn có tồn tại không
        Hotel hotelEntity = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        // Xóa các hình ảnh liên quan đến khách sạn
        List<HotelImage> hotelImages = hotelImageRepository.findByHotelId(hotelEntity);
        hotelImageRepository.deleteAll(hotelImages);

        // Xóa địa chỉ của khách sạn
        HotelAddress hotelAddress = hotelEntity.getHotelAddress();
        if (hotelAddress != null) {
            hotelAddressRepository.delete(hotelAddress);
        }

        // Xóa khách sạn
        hotelRepository.delete(hotelEntity);
        System.out.println("Khách sạn có id " + hotelId + " đã được xóa thành công.");
    }
    public HotelDTO updateHotel(int id, HotelDTO hotelDTO) {
        // Kiểm tra xem khách sạn có tồn tại không
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khách sạn không tồn tại"));

        // Cập nhật thông tin từ DTO
        existingHotel.setName(hotelDTO.getName());
        existingHotel.setDescription(hotelDTO.getDescription());
        existingHotel.setCheckInTime(hotelDTO.getCheckInTime());
        existingHotel.setCheckOutTime(hotelDTO.getCheckOutTime());
        existingHotel.setCloseTime(hotelDTO.getCloseTime());
        existingHotel.setOpenTime(hotelDTO.getOpenTime());
        existingHotel.setPhone(hotelDTO.getPhone());
        existingHotel.setRating(hotelDTO.getRating());

        // Cập nhật thông tin địa chỉ từ DTO
        if (hotelDTO.getHotelAddressDTO() != null) {
            HotelAddressDTO addressDTO = hotelDTO.getHotelAddressDTO();
            HotelAddress addressEntity = existingHotel.getHotelAddress();
            if (addressEntity == null) {
                addressEntity = new HotelAddress();
                addressEntity.setHotelIdFk(existingHotel);
            }
            addressEntity.setStreetNumber(addressDTO.getStreetNumber());
            addressEntity.setStreetName(addressDTO.getStreetName());
            addressEntity.setDistrict(addressDTO.getDistrict());
            addressEntity.setCity(addressDTO.getCity());
            addressEntity.setProvince(addressDTO.getProvince());
            addressEntity.setCountry(addressDTO.getCountry());
            existingHotel.setHotelAddress(addressEntity);
        }

        // Cập nhật thông tin hình ảnh từ DTO
        List<HotelImageDTO> imageDTOs = hotelDTO.getListHotelImageDTO();
        if (imageDTOs != null && !imageDTOs.isEmpty()) {
            List<HotelImage> imageEntities = new ArrayList<>();
            for (HotelImageDTO imageDTO : imageDTOs) {
                HotelImage imageEntity = new HotelImage();
                imageEntity.setHotelId(existingHotel);
                imageEntity.setImageTitle(imageDTO.getImageTitle());
                imageEntity.setImageDescription(imageDTO.getImageDescription());
                imageEntity.setImagePath(imageDTO.getImagePath());
                imageEntity.setUploadDate(imageDTO.getUploadDate());
                imageEntities.add(imageEntity);
            }
            existingHotel.setHotelImages(imageEntities);
        }

        // Lưu thay đổi vào cơ sở dữ liệu
        hotelRepository.save(existingHotel);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cập nhật khách sạn thành công");
        response.put("updatedHotel", existingHotel);

        // Trả về DTO đã được cập nhật
        return hotelDTO;
    }
    public List<HotelDTO> getAllHotelsByMapper() {
        List<Hotel> hotelEntities = hotelRepository.findAll();
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        for (Hotel hotelEntity : hotelEntities) {
            HotelDTO hotelDTO =    modelMapper.map(hotelEntity,HotelDTO.class);
            if(hotelEntity.getHotelAddress() != null){
                HotelAddressDTO hotelAddressDTO = modelMapper.map(hotelEntity.getHotelAddress(),HotelAddressDTO.class);
                hotelDTO.setHotelAddressDTO(hotelAddressDTO);
            }
        }
        return hotelDTOs;
    }
    public HotelDTO addHotelByMapper(HotelDTO hotelDTO) {
        // Tạo đối tượng ModelMapper
        ModelMapper modelMapper = new ModelMapper();
        // Ánh xạ từ HotelDTO sang Hotel entity
        Hotel hotelEntity = modelMapper.map(hotelDTO, Hotel.class);
        // Tìm kiếm và thiết lập người dùng liên quan đến khách sạn
        User userEntity = userRepository.findById(hotelDTO.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        hotelEntity.setUserId(userEntity);
        // Lưu đối tượng khách sạn mới vào cơ sở dữ liệu
        Hotel savedHotelEntity = hotelRepository.save(hotelEntity);

        // Xử lý thông tin địa chỉ của khách sạn nếu có
        if (hotelDTO.getHotelAddressDTO() != null) {
            HotelAddress addressEntity = modelMapper.map(hotelDTO.getHotelAddressDTO(), HotelAddress.class);
            addressEntity.setHotelIdFk(savedHotelEntity);
            hotelAddressRepository.save(addressEntity);
        }
        // Xử lý thông tin hình ảnh của khách sạn nếu có
        if (hotelDTO.getListHotelImageDTO() != null) {
            for (HotelImageDTO imageDTO : hotelDTO.getListHotelImageDTO()) {
                HotelImage imageEntity = modelMapper.map(imageDTO, HotelImage.class);
                imageEntity.setHotelId(savedHotelEntity);
                hotelImageRepository.save(imageEntity);
            }
        }
        // Trả về DTO đã được lưu
        return modelMapper.map(savedHotelEntity, HotelDTO.class);
    }

    public HotelDTO updateHotelByMapper(int id, HotelDTO hotelDTO) {
        // Kiểm tra xem khách sạn có tồn tại không
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khách sạn không tồn tại"));

        // Cập nhật thông tin từ DTO
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(hotelDTO, existingHotel);

        // Cập nhật thông tin địa chỉ từ DTO
        if (hotelDTO.getHotelAddressDTO() != null) {
            HotelAddressDTO addressDTO = hotelDTO.getHotelAddressDTO();
            HotelAddress addressEntity = existingHotel.getHotelAddress();
            if (addressEntity == null) {
                addressEntity = new HotelAddress();
                addressEntity.setHotelIdFk(existingHotel);
            }
            modelMapper.map(addressDTO, addressEntity);
            existingHotel.setHotelAddress(addressEntity);
        }

        // Cập nhật thông tin hình ảnh từ DTO
        List<HotelImageDTO> imageDTOs = hotelDTO.getListHotelImageDTO();
        if (imageDTOs != null && !imageDTOs.isEmpty()) {
            List<HotelImage> imageEntities = new ArrayList<>();
            for (HotelImageDTO imageDTO : imageDTOs) {
                HotelImage imageEntity = modelMapper.map(imageDTO, HotelImage.class);
                imageEntity.setHotelId(existingHotel);
                imageEntities.add(imageEntity);
            }
            existingHotel.setHotelImages(imageEntities);
        }

        // Lưu thay đổi vào cơ sở dữ liệu
        Hotel updatedHotel = hotelRepository.save(existingHotel);

        // Trả về DTO đã được cập nhật
        return modelMapper.map(updatedHotel, HotelDTO.class);
    }

}
