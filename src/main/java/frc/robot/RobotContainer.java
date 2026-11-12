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

import frc.robot.subsystems.TurretSubsystem;

public class RobotContainer {
  public final TurretSubsystem m_turretSubsystem;

  private final GenericHID m_controller = new GenericHID(OperatorConstants.kOperatorControllerPort);

  public RobotContainer() {
    m_turretSubsystem = new TurretSubsystem();
    configureBindings();
  }

  private void configureBindings() {

    new JoystickButton(m_controller, 1).onTrue(new InstantCommand(() -> m_turretSubsystem.setAngle(90)));
    new JoystickButton(m_controller, 2).onTrue(new InstantCommand(() -> m_turretSubsystem.setAngle(180)));
    new JoystickButton(m_controller, 3).onTrue(new InstantCommand(() -> m_turretSubsystem.setAngle(-90)));
    new JoystickButton(m_controller, 4).onTrue(new InstantCommand(() -> m_turretSubsystem.setAngle(-180)));
    new JoystickButton(m_controller, 5).onTrue(new InstantCommand(() -> m_turretSubsystem.setAngle(0)));

  }

  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new InstantCommand(() -> m_turretSubsystem.setAngle(90)),
      new WaitCommand(1.5),
      new InstantCommand(() -> m_turretSubsystem.setAngle(-90)),
      new WaitCommand(1.5),
      new InstantCommand(() -> m_turretSubsystem.setAngle(0))
    );
  }
}
