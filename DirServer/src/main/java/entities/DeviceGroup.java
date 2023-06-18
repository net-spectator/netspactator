package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "device_group")
public class DeviceGroup {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    private int id;

    @Column
    private String title;

    @OneToMany(mappedBy = "deviceGroup", cascade = CascadeType.ALL)
    private List<TrackedEquipment> device;

}
