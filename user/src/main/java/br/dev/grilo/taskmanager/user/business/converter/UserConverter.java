package br.dev.grilo.taskmanager.user.business.converter;

import br.dev.grilo.taskmanager.user.business.dto.AddressDTO;
import br.dev.grilo.taskmanager.user.business.dto.PhoneDTO;
import br.dev.grilo.taskmanager.user.business.dto.UserDTO;
import br.dev.grilo.taskmanager.user.infra.entity.Address;
import br.dev.grilo.taskmanager.user.infra.entity.Phone;
import br.dev.grilo.taskmanager.user.infra.entity.User;
import org.springframework.stereotype.Component;

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
                active(userDTO.isActive()).
                blocked(userDTO.isBlocked()).
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
        return phoneDTOList.stream().map(this::toPhone).toList();
    }


    // DTO to Entity

    public UserDTO toUserDTO(User user) {
        return UserDTO.builder().
                name(user.getName()).
                username(user.getUsername()).
                email(user.getEmail()).
                password(user.getPassword()).
                active(user.isActive()).
                blocked(user.isBlocked()).
                createdAt(user.getCreatedAt()).
                updatedAt(user.getUpdatedAt()).
                addresses(toListAddressDTO(user.getAddresses())).
                phones(toListPhoneDTO(user.getPhones())).
                build();
    }

    public AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder().
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
        return addressList.stream().map(this::toAddressDTO).toList();
    }

    public PhoneDTO toPhoneDTO(Phone phone) {
        return PhoneDTO.builder().
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
        return phoneList.stream().map(this::toPhoneDTO).toList();
    }



}
