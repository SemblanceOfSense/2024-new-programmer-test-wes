package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    // Left motor: 13
    // Right motor: 14
    private final CANSparkFlex left = new CANSparkFlex(13, MotorType.kBrushless);
    private final CANSparkFlex right = new CANSparkFlex(14, MotorType.kBrushless);

    private final PIDController leftPid = new PIDController(0.5, 0, 0);
    private final PIDController rightPid = new PIDController(0.5, 0, 0);

    private final RelativeEncoder leftEncoder = left.getEncoder();
    private final RelativeEncoder rightEncoder = right.getEncoder();

    // Digitalinput id: 4, 5
    DigitalInput breaker = new DigitalInput(4);

    public IntakeSubsystem() {
        //Start stuff
    }

    public void setLeftMotor(double rots) {
        left.setVoltage(leftPid.calculate(leftEncoder.getPosition(), rots));
    }

    public void setRightMotor(double rots) {
        right.setVoltage(rightPid.calculate(rightEncoder.getPosition(), rots));
    }

    public void go() {
        setLeftMotor(5);
        setRightMotor(5);
    }

    public boolean isAtLine() {
        return breaker.get();
    }
}
