import org.rioproject.config.Constants

deployment(name: 'Test') {
    groups System.getProperty(Constants.GROUPS_PROPERTY_NAME, System.getProperty('user.name'))

    artifact id: 'outrigger-dl',   'org.apache.river:outrigger-dl:2.2.2'
    artifact id: 'outrigger-impl', 'org.apache.river:outrigger:2.2.2'

    service(name: 'Outrigger') {
        interfaces {
            classes "net.jini.space.JavaSpace"
            /*resources 'test/outrigger-dl-2.2.2.jar'*/
            artifact ref: 'outrigger-dl'
        }
        implementation(class: "com.sun.jini.outrigger.TransientOutriggerImpl") {
            /*resources 'test/outrigger-2.2.2.jar'
            resources 'test/outrigger-dl-2.2.2.jar'
            resources 'test/jsk-dl-2.2.2.jar'*/
            artifact ref: 'outrigger-impl'
        }
        maintain 1
        maxPerMachine 1
    }

}
