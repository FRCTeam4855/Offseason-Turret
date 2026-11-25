package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class FindZeroCommand extends Command {
  private final TurretSubsystem m_turretSubsystem;

  private boolean magnet;

  public FindZeroCommand(TurretSubsystem turretSubsystem) {
    m_turretSubsystem = turretSubsystem;
    addRequirements(m_turretSubsystem);
  }

  @Override
  public void initialize() {
    System.out.println("Finding Zero");
    m_turretSubsystem.moveAtVelocityCommand(20);
  }

  @Override
  public void execute() {
    // Keep moving until the limit switch is triggered
    magnet = Robot.magnet;
  }

  @Override
  public boolean isFinished() {
    return !magnet;
  }

  @Override
  public void end(boolean interrupted) {
    m_turretSubsystem.setVelocity(0);
    if (!interrupted) {
      System.out.println("FOUND");
      m_turretSubsystem.resetEncoder();
    }
  }

}
