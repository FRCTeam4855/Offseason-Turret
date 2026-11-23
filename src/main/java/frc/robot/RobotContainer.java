// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.SensorConstants;

import static edu.wpi.first.units.Units.RPM;

import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.FindZeroCommand;

public class RobotContainer {
  public final TurretSubsystem m_turretSubsystem;
  public final ShooterSubsystem m_shooterSubsystem;

  private final GenericHID m_controller = new GenericHID(OperatorConstants.kOperatorControllerPort);

  public RobotContainer() {
    m_turretSubsystem = new TurretSubsystem();
    m_shooterSubsystem = new ShooterSubsystem(); 
    configureBindings();

    m_shooterSubsystem.setDefaultCommand(new RunCommand(
        () -> m_shooterSubsystem.set(0), m_shooterSubsystem));
  }

  private void configureBindings() {

    new JoystickButton(m_controller, 1).onTrue(m_turretSubsystem.moveToAngleCommand(90));
    new JoystickButton(m_controller, 2).onTrue(m_turretSubsystem.moveToAngleCommand(180));
    new JoystickButton(m_controller, 3).onTrue(m_turretSubsystem.moveToAngleCommand(-90));
    new JoystickButton(m_controller, 4).onTrue(m_turretSubsystem.moveToAngleCommand(-180));
    new JoystickButton(m_controller, 5).onTrue(m_turretSubsystem.moveToAngleCommand(0));

    new JoystickButton(m_controller, 21).onTrue(m_shooterSubsystem.run(RPM.of(2000)));
    new JoystickButton(m_controller, 22).onTrue(m_shooterSubsystem.run(RPM.of(0)));
    new JoystickButton(m_controller, 19).onTrue(m_shooterSubsystem.run(RPM.of(2500)));
    new JoystickButton(m_controller, 20).onTrue(m_shooterSubsystem.run(RPM.of(1500)));
    new JoystickButton(m_controller, 23).onTrue(new FindZeroCommand(m_turretSubsystem));
  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
        m_turretSubsystem.moveToAngleCommand(90),
        new WaitCommand(3),
        m_turretSubsystem.moveToAngleCommand(180),
        new WaitCommand(3),
        m_turretSubsystem.moveToAngleCommand(-90),
        new WaitCommand(3),
        m_turretSubsystem.moveToAngleCommand(-180),
        new WaitCommand(3),
        m_turretSubsystem.moveToAngleCommand(0));
  }
}
