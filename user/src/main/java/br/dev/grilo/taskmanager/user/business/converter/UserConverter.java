package br.dev.grilo.taskmanager.user.business.converter;

import br.dev.grilo.taskmanager.user.business.dto.AddressDTO;
import br.dev.grilo.taskmanager.user.business.dto.PhoneDTO;
import br.dev.grilo.taskmanager.user.business.dto.UserDTO;
import br.dev.grilo.taskmanager.user.infra.entity.Address;
import br.dev.grilo.taskmanager.user.infra.entity.Phone;
import br.dev.grilo.taskmanager.user.infra.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserConverter {

    // Entity to DTO

    public User toUser(UserDTO userDTO) {
        return User.builder().
                name(userDTO.getName()).
                username(userDTO.getUsername()).
                email(userDTO.getEmail()).
                password(userDTO.getPassword()).
                createdAt(userDTO.getCreatedAt()).
                updatedAt(userDTO.getUpdatedAt()).
                addresses(toListAddress(userDTO.getAddresses())).
                phones(toListPhone(userDTO.getPhones())).
                build();
    }

    public Address toAddress(AddressDTO addressDTO) {
        return Address.builder().
                zipCode(addressDTO.getZipCode()).
                address(addressDTO.getAddress()).
                number(addressDTO.getNumber()).
                complement(addressDTO.getComplement()).
                neighborhood(addressDTO.getNeighborhood()).
                extraInformation(addressDTO.getExtraInformation()).
                country(addressDTO.getCountry()).
                countryProvince(addressDTO.getCountryProvince()).
                city(addressDTO.getCity()).
                build();
    }

    public List<Address> toListAddress(List<AddressDTO> addressDTOList) {
        if (addressDTOList == null) {
            return Collections.emptyList();
        }

        return addressDTOList.stream().map(this::toAddress).toList();
    }

    public Phone toPhone(PhoneDTO phoneDTO) {
        return Phone.builder().
                createdAt(phoneDTO.getCreatedAt()).
                updatedAt(phoneDTO.getUpdatedAt()).
                countryCode(phoneDTO.getCountryCode()).
                phoneNumber(phoneDTO.getPhoneNumber()).
                label(phoneDTO.getLabel()).
                isPublic(phoneDTO.isPublic()).
                active(phoneDTO.isActive()).
                build();
    }

    public List<Phone> toListPhone(List<PhoneDTO> phoneDTOList) {
        if (phoneDTOList == null) {
            return Collections.emptyList();
        }

        return phoneDTOList.stream().map(this::toPhone).toList();
    }


    // DTO to Entity

    public UserDTO toUserDTO(User user) {
        return UserDTO.builder().
                name(user.getName()).
                username(user.getUsername()).
                email(user.getEmail()).
                password(user.getPassword()).
                createdAt(user.getCreatedAt()).
                updatedAt(user.getUpdatedAt()).
                addresses(toListAddressDTO(user.getAddresses())).
                phones(toListPhoneDTO(user.getPhones())).
                build();
    }

    public AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder().
                id(address.getId()).
                zipCode(address.getZipCode()).
                address(address.getAddress()).
                number(address.getNumber()).
                complement(address.getComplement()).
                neighborhood(address.getNeighborhood()).
                extraInformation(address.getExtraInformation()).
                country(address.getCountry()).
                countryProvince(address.getCountryProvince()).
                city(address.getCity()).
                build();
    }

    public List<AddressDTO> toListAddressDTO(List<Address> addressList) {
        if (addressList == null) {
            return Collections.emptyList();
        }

        return addressList.stream().map(this::toAddressDTO).toList();
    }

    public PhoneDTO toPhoneDTO(Phone phone) {
        return PhoneDTO.builder().
                id(phone.getId()).
                createdAt(phone.getCreatedAt()).
                updatedAt(phone.getUpdatedAt()).
                countryCode(phone.getCountryCode()).
                phoneNumber(phone.getPhoneNumber()).
                label(phone.getLabel()).
                isPublic(phone.isPublic()).
                active(phone.isActive()).
                build();
    }

    public List<PhoneDTO> toListPhoneDTO(List<Phone> phoneList) {
        if (phoneList == null) {
            return Collections.emptyList();
        }
        return phoneList.stream().map(this::toPhoneDTO).toList();
    }

    public User updateUser(UserDTO dto, User entity) {
        return User.builder()
                .id(entity.getId())
                .name(dto.getName() != null ? dto.getName() : entity.getName())
                .username(dto.getUsername() != null ? dto.getUsername() : entity.getUsername())
                .email(dto.getEmail() != null ? dto.getEmail() : entity.getEmail())
                .password(dto.getPassword() != null ? dto.getPassword() : entity.getPassword())
                .createdAt(entity.getCreatedAt())
                .updatedAt(System.currentTimeMillis())
                .addresses(entity.getAddresses())
                .phones(entity.getPhones())
                .build();
    }

    public Address updateAddress(AddressDTO dto, Address entity) {
        return Address.builder()
                .id(entity.getId())
                .zipCode(dto.getZipCode() != null ? dto.getZipCode() : entity.getZipCode())
                .address(dto.getAddress() != null ? dto.getAddress() : entity.getAddress())
                .number(dto.getNumber() != null ? dto.getNumber() : entity.getNumber())
                .complement(dto.getComplement() != null ? dto.getComplement() : entity.getComplement())
                .neighborhood(dto.getNeighborhood() != null ? dto.getNeighborhood() : entity.getNeighborhood())
                .extraInformation(dto.getExtraInformation() != null ? dto.getExtraInformation() : entity.getExtraInformation())
                .country(dto.getCountry() != null ? dto.getCountry() : entity.getCountry())
                .countryProvince(dto.getCountryProvince() != null ? dto.getCountryProvince() : entity.getCountryProvince())
                .city(dto.getCity() != null ? dto.getCity() : entity.getCity())
                .build();
    }

    public Phone updatePhone(PhoneDTO dto, Phone entity) {
        return Phone.builder()
                .id(entity.getId())
                .countryCode(dto.getCountryCode() != null ? dto.getCountryCode() : entity.getCountryCode())
                .phoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : entity.getPhoneNumber())
                .label(dto.getLabel() != null ? dto.getLabel() : entity.getLabel())
                .isPublic(dto.isPublic() || entity.isPublic())
                .active(dto.isActive() || entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(System.currentTimeMillis())
                .build();
    }


}
